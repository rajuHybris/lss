package demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.entity.UserEntity;


public interface UserService {

	 ResponseEntity<?> saveUser(UserEntity userEntity);

	 List<UserEntity> getAllUser();

	String updateUserById(UserEntity userEntity, Long userId);

	String deleteUserById(Long userId);

	UserEntity getUserById(Long userId);

}
