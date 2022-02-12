package com.udemy.book_seller.security.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.udemy.book_seller.model.Role;
import com.udemy.book_seller.security.jwt.InternaApiAuthenticationFilter;
import com.udemy.book_seller.security.jwt.JwtAuthorizationFilter;


@Configurable  //bec we will have soem config beans
@EnableWebSecurity  //tells Spring that this is the spring security config class
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Value("${authentication.internal-api-key}")
	private String internalAccessKey;
	
	@Bean
	public InternaApiAuthenticationFilter beanInternaApiAuthenticationFilter() {
		return new InternaApiAuthenticationFilter(internalAccessKey);
	}
	
	@Bean   //neede injected in UserService to encrypt stored passwords
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
		return new JwtAuthorizationFilter();  //from my package security.jwt
	}
	
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//define CORS -> which methods/paths/IP are allowedby sending back Access-Control-Allow-Origin with http://repotest.ub.de
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("*"); //allow all methods, paths and OriginURL 
			}
		};
	}
	
	
	@Override  //which Service and Encoder to use for Authentication?
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{		
		//JWT Login:
		//user makes a POST with Username and Password to /api/authentication
		//check via oncePerRequest Filter the credentials from DB
		//if ok, generate JwtToken 
		
		http.cors();
		http.csrf().disable(); //hackers can steal our session info from cookuie -> but we use JWT for info abt user, JWtsent with each header Authoriation
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //dont use Session, we use JWT to send info abt user and session
		
		http.authorizeRequests() //authorization is enabled for all user ROLES -> USer, ADMIN, MANAGER
			.antMatchers("/api/authentication/**").permitAll()  //permit all req here for user to login or register
			.antMatchers(HttpMethod.GET, "/api/book").permitAll()
			.antMatchers("/api/book/**").hasRole(Role.ADMIN.name())
			.antMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name()) //only SYS_ADMIN can access internal path
			.anyRequest().authenticated();    //and any other req should be authenticated
		
		//add InternalFilter(Admin) before ourJwtFilter, otherwise it has no more token to test for Admin authorization
		//internal filter will work only for /api/internal/**, while jwt for all other paths
		//add JwtFilter before USernameAndPassword(default Spring Form filter) filter //if other order -> JwtAuth fails
		http.addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(beanInternaApiAuthenticationFilter(), JwtAuthorizationFilter.class);
	}
	
}
