package com.devhub.devhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devhub.devhub.model.AppUser;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
