package demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);

}
