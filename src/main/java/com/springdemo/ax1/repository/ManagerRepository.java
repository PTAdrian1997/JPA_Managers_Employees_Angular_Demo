package com.springdemo.ax1.repository;

import com.springdemo.ax1.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
