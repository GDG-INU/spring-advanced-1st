package com.gdg.ms.repository;

import com.gdg.ms.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findById(String id);
}
