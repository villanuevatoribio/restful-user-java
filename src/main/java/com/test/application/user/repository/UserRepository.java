package com.test.application.user.repository;
import java.util.Optional;

import com.test.application.user.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase repository
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

}
