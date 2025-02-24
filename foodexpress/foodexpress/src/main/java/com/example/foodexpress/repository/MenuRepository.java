package com.example.foodexpress.repository;

import com.example.foodexpress.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByName(String name); // Custom query to find menu item by name
}
