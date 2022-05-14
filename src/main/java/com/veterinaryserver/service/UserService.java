package com.veterinaryserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.entity.user.UserRole;
import com.veterinaryserver.exception.ErrorCode;
import com.veterinaryserver.exception.VeterinaryException;
import com.veterinaryserver.model.request.LoginRequest;
import com.veterinaryserver.model.request.RegistrationRequest;
import com.veterinaryserver.model.request.UserUpdateRequest;
import com.veterinaryserver.model.response.UserAndAnimalsResponse;
import com.veterinaryserver.repository.AnimalRepository;
import com.veterinaryserver.repository.UserRepository;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	
	
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private ErrorMessagesService errorMessagesService;
	private AnimalRepository animalRepository;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			ErrorMessagesService errorMessagesService, AnimalRepository animalRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.errorMessagesService = errorMessagesService;
		this.animalRepository = animalRepository;
	}

	public User register(RegistrationRequest request) {
	
		Boolean exists = userRepository.existsByUsername(request.getUsername());
		if(exists) {
			logger.info("Exception, {}, {}",ErrorCode.USERNAME_ALREADY_IN_USAGE, this.errorMessagesService.getMessage(ErrorCode.USERNAME_ALREADY_IN_USAGE));
			throw new VeterinaryException(ErrorCode.USERNAME_ALREADY_IN_USAGE, this.errorMessagesService.getMessage(ErrorCode.USERNAME_ALREADY_IN_USAGE));
		}
		
		User user = parseRegistrationRequestToUser(request);
		
		this.userRepository.save(user);
		return user;
	}
	
	public User findById(Long id) {
		User user = this.userRepository.findById(id).orElseThrow(() -> new VeterinaryException(ErrorCode.NO_RESOURCE, 
				this.errorMessagesService.getMessage(ErrorCode.NO_RESOURCE)));
		logger.info("User find by id, {}, {}, {}", id, user.getId(), user.getName());
		return user;
	}
	
	
	/**
	 * Find user by email.
	 * 
	 * @param email
	 * @return User
	 * @throws VeterinaryException with 410 Status Code
	 */
	public User findByUsername(String username) {
		logger.info("Find by username, {}", username);
		return this.userRepository.findByUsername(username).orElseThrow(() -> new VeterinaryException(ErrorCode.NO_RESOURCE,
				this.errorMessagesService.messages.get(ErrorCode.NO_RESOURCE)));
	}

	public UserAndAnimalsResponse findUserAndUserAnimalsWithUserId(Long id) {
		User user = this.findById(id);
		List<Animal> userAnimals = this.animalRepository.findAllByUserId(id);
		return UserAndAnimalsResponse.builder()
				.user(user)
				.animals(userAnimals).build();
	}

	public List<User> searchUser(String q) {
		List<User> users = this.userRepository.searchAll(q);
		return users;
	}

	public UserAndAnimalsResponse userProfile() {
		User user = this.getLoggedInUser();
		UserAndAnimalsResponse response = this.findUserAndUserAnimalsWithUserId(user.getId());
		return response;
	}

	public User getLoggedInUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = this.findByUsername(username);
		logger.info("Logged In User username, {}", username);
		return user;
	}
	
	public User updateUser(Long id, UserUpdateRequest request) {
		User loggedInUser = this.getLoggedInUser();
		User user = this.findById(id);
		logger.info("Update User - find, {}", user.toString());
		
		if(!loggedInUser.equals(user) && !loggedInUser.getRole().equals(UserRole.ADMIN)) {
			logger.info("LoggedInUser, {}", loggedInUser.toString());
			logger.info("User, {}", user.toString());
			logger.info("FORBIDDEN");
			throw new VeterinaryException(ErrorCode.FORBIDDEN, this.errorMessagesService.getMessage(ErrorCode.FORBIDDEN));
		}
		
		logger.info("Update User - request, {}", request.toString());
		User updated = this.updateUserWithUserUpdateRequest(user, request);
		User saved = this.userRepository.save(updated);
		logger.info("Update User - updated, {}", updated.toString());
		logger.info("Saved user - saved, {}", saved.toString());
		return saved;
	}

	public User updateUserWithUserUpdateRequest(User user, UserUpdateRequest request) {
		if(!ObjectUtils.isEmpty(request.getName()) && !(request.getName().equals(user.getName()))){
			user.setName(request.getName());
		}if(!ObjectUtils.isEmpty(request.getSurname()) && !(request.getSurname().equals(user.getSurname()))){
			user.setSurname(request.getSurname());
		}if(!ObjectUtils.isEmpty(request.getUsername()) && !(request.getUsername().equals(user.getUsername()))) {
			user.setUsername(request.getUsername());
		}if(!ObjectUtils.isEmpty(request.getPhoneNumber()) && !(request.getPhoneNumber().equals(user.getPhoneNumber()))) {
			user.setPhoneNumber(request.getPhoneNumber());
		}
		return user;
	}
	
	public User parseRegistrationRequestToUser(RegistrationRequest request) {
		if(ObjectUtils.isEmpty(request.getName()) || 
				ObjectUtils.isEmpty(request.getSurname()) ||
				ObjectUtils.isEmpty(request.getUsername()) ||
				ObjectUtils.isEmpty(request.getPhoneNumber()) ||
				ObjectUtils.isEmpty(request.getPassword())) {
				logger.info("Given user creation request has empty fields");
				throw new VeterinaryException(ErrorCode.BAD_REQUEST, this.errorMessagesService.getMessage(ErrorCode.BAD_REQUEST));
			}
		return User.builder()
				.name(request.getName())
				.surname(request.getSurname())
				.username(request.getUsername())
				.phoneNumber(request.getPhoneNumber())
				.password(this.passwordEncoder.encode(request.getPassword()))
				.role(UserRole.USER)
				.build();
	}
	
}
