package com.veterinaryserver.entity.animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.veterinaryserver.entity.BaseEntity;
import com.veterinaryserver.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "animals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Animal extends BaseEntity{
	
	@Column(nullable = false, name = "animal_name")
	private String name;
	
	@Column(nullable = false)
	private Short age;
	
	@Column(nullable = false)
	private String breed;
	
	@Column(name = "animal_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AnimalType type;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Lob
	private String about;
	
}
