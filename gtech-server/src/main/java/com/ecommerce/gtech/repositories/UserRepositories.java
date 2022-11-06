package com.ecommerce.gtech.repositories;

import com.ecommerce.gtech.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories  extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.* FROM User u  INNER JOIN customer c ON u.id=c.id  WHERE c.email = :email"
            , nativeQuery = true)
    User findUserByEmail(String email);
}
