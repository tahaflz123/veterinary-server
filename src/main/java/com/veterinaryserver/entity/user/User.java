package com.veterinaryserver.entity.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.Nullable;
import com.veterinaryserver.entity.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity implements UserDetails{
	
	@Column(name = "user_name", nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false, name = "email")
	private String username;
	
	private String phoneNumber;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	private GrantedAuthority grantedAuthority;

	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;
	
	private boolean credentialsNonExpired = true;
	
	private boolean enabled = true;
	
	@Builder
	public User(String name, String surname, String username, String phoneNumber, String password, UserRole role) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		grantedAuthority = new SimpleGrantedAuthority(this.role.name());
		return List.of(grantedAuthority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	/*class UserBuilder{
		
		private String name;
		
		private String surname;
		
		private String username;
		
		private String email;
		
		private String phoneNumber;
		
		private String password;
		
		private UserRole role;
		
		public UserBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public UserBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder role(UserRole role) {
			this.role = role;
			return this;
		}
		
		public User build() {
			return new User(name, surname,username, email, phoneNumber, password, role);
		}
	}*/
}