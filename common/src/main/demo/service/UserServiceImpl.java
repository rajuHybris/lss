package demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.entity.UserEntity;
import demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

//Create User
	@Override
	public ResponseEntity<?> saveUser(UserEntity userEntity) {
		try {
			Optional<UserEntity> findByName = userRepository.findByUserName(userEntity.getUserName());
			if (!findByName.isEmpty() && findByName.get() != null) {
				throw new Exception("Name Already Exist!!");
			}
			return ResponseEntity.ok(userRepository.save(userEntity));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

//Read User
	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}

//Update User
	@Override
	public String updateUserById(UserEntity userEntity, Long userId) {
		Optional<UserEntity> entity = userRepository.findById(userId);
		if (!entity.equals(null)) {
			userRepository.save(userEntity);
			return "User succesfully updated";
		} else
			return "Id doesn't exist";
	}

//Delete User
	@Override
	public String deleteUserById(Long userId) {
		userRepository.deleteById(userId);
		return "User succesfully Deleted";

	}

	@Override
	public UserEntity getUserById(Long userId) {

		return userRepository.findById(userId).get();
	}

}
