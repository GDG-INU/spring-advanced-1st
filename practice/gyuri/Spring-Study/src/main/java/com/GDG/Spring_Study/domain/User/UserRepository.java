package com.GDG.Spring_Study.domain.User;

import com.GDG.Spring_Study.entitiy.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}