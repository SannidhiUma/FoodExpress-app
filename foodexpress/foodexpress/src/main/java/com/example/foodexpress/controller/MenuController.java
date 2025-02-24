package com.example.foodexpress.controller;

import com.example.foodexpress.model.MenuItem;
import com.example.foodexpress.repository.MenuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Add a new menu item (for admin) with image upload
    @PostMapping("/admin/addMenuItem")
    public ResponseEntity<List<MenuItem>> addMenuItem(
            @RequestParam String name, 
            @RequestParam String description, 
            @RequestParam double price, 
            @RequestParam MultipartFile image) {
        try {
            // Process the uploaded image (save it to the server and generate a URL)
            String imageUrl = saveImage(image); 

            // Create the menu item object
            MenuItem menuItem = new MenuItem(name, description, price, imageUrl);
            menuRepository.save(menuItem);

            // Return the updated list of all menu items after adding the new item
            List<MenuItem> menuItems = menuRepository.findAll(); 
            return ResponseEntity.ok(menuItems); // Respond with the list of menu items

        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Get all menu items (for customer view)
    @GetMapping("/index")
    public List<MenuItem> getMenuItems() {
        return menuRepository.findAll(); // Fetch all menu items from the database
    }

    // Helper method to save the image to the server and return the image URL
    private String saveImage(MultipartFile image) throws IOException {
        // Define the directory where the images will be saved
        String uploadDir = "/path/to/save/images/";

        // Ensure the directory exists
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path); // Create the directory if it does not exist
        }

        // Save the image to the directory
        String imageFileName = image.getOriginalFilename();
        Path imagePath = path.resolve(imageFileName);
        image.transferTo(imagePath.toFile());

        // Return the relative URL of the saved image
        return "/images/" + imageFileName;  // Assuming you will serve images from this path
    }
}
