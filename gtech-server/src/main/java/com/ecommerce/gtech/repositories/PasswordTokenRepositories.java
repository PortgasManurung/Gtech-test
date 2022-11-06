package com.ecommerce.gtech.repositories;

import com.ecommerce.gtech.models.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenRepositories extends JpaRepository<PasswordToken, Long> {
     Optional<PasswordToken> findUserByToken(String token);
}
