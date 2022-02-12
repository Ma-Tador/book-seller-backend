package com.udemy.book_seller.model;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="username", unique = true, nullable = false, length = 100)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)   //in DB is stored the val of enum as String, not as nr ex.ADMIN(not nr '1')
	@Column(name = "role")//, nullable = false)
	private Role role;
	
	@Column(name="time")//, nullable = false)
	private LocalDateTime createTime;
	
	@Transient //dont need to store in DB
	private String jwtToken;
	
	
	//get and set for JPA to access 


	public User(String username, String password, String name, Role role, LocalDateTime createTime, String jwtToken) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.createTime = createTime;
		this.jwtToken = jwtToken;
	}
	
	public User() {};
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
