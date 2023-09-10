package com.testing.testing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{

    User findByEmailAndPassword(String email, Integer password);
}