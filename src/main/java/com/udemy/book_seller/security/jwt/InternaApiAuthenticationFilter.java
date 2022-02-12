package com.udemy.book_seller.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.udemy.book_seller.security.basic.CustomUserDetails;
import com.udemy.book_seller.util.SecurityUtil;

//is a Component -> create private String accessKey in SecurityConfig
public class InternaApiAuthenticationFilter extends OncePerRequestFilter {

	Logger logger = Logger.getLogger(InternaApiAuthenticationFilter.class);

	private String accessKey;

	public InternaApiAuthenticationFilter(String acessKey) {
		this.accessKey = acessKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// code exec by a given impl filter, if code wasnt applied yet
			// used here to filter by authentication.internal-api-key(def in app.prop) to
			// see if has privilege to get ROLE = ADMIN
			String requestKey = SecurityUtil.extractAuthTokenFromRequest(request);
			if (requestKey == null || !requestKey.equals(this.accessKey)) {
				logger.warn("Internal key is wrong: " + requestKey);
				throw new RuntimeException("Unauthorized request. Wrong request key");
			}
			CustomUserDetails admin = CustomUserDetails.getAdminUser();
			// need to inform the SecurityContext that we have ADMIN authenticated, but
			// SecurityContext expects authToken
			// so we convert CustomUser to AuthTOken ->use provider of auth:
			// UsernamePasswordAuthenticationToken
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(admin, null,
					admin.getAuthorities());
			// now we inform the SecurityContext that we have other auth user (ADMIN)
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			logger.error("Cant set user admin in SecurityContextHolder");
		}
		filterChain.doFilter(request, response);
		//requestAuth -> thisInternalFilter -> ourJwtAuthFilter -> UsernamePasswordAuthFilter(default FormAuth in Spring)
		//need to include our internalFilter in Spring's AuthFilter, and specify the order (before ourJwtAuthFilter)
		//can config order of filter in SecurityConfig, configure(), http.addFilterBefore(internalFilter, jwtFilter)
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getRequestURI().startsWith("/api/internal");
	}
}
