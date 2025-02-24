package com.example.foodexpress.service;

import com.example.foodexpress.model.MenuItem;
import com.example.foodexpress.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Add a new menu item
    public void addMenuItem(MenuItem item) {
        menuRepository.save(item);
    }

    // Get all menu items
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    // Delete menu item by name
    public void deleteMenuItem(String name) {
        Optional<MenuItem> menuItem = menuRepository.findByName(name);
        menuItem.ifPresent(menuRepository::delete);
    }
}
