package com.hb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);

}
