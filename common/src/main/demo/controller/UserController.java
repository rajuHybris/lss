package demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.UserEntity;
import demo.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/save")
	public ResponseEntity<?> saveUser( UserEntity userEntity) {
		try {
		return this.userService.saveUser(userEntity);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),   HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	}

	@GetMapping("/fetch")
	public List<UserEntity> getUser() {
		return this.userService.getAllUser();
	}

	@GetMapping("/fetch/{userId}")
	public UserEntity getUserById(@PathVariable Long userId) {
		return this.userService.getUserById(userId);
	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return this.userService.deleteUserById(userId);

	}

	@PutMapping("/update/{userId}")
	public String updateUser(UserEntity userEntity, Long userId) {
		return this.userService.updateUserById(userEntity, userId);
	}

}
