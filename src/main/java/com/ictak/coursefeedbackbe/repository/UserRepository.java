package com.ictak.coursefeedbackbe.repository;

import com.ictak.coursefeedbackbe.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * FROM users WHERE email=?1  and password=?2 ",nativeQuery = true)
    Users login(String email, String password);
}
