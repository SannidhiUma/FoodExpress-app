    package com.example.foodexpress.controller;

    import com.example.foodexpress.model.User;
    import com.example.foodexpress.service.UserService;
    import com.razorpay.RazorpayClient;
    import com.razorpay.Order;
    
    import org.json.JSONObject;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

import java.util.Map;
    
    @Controller
    public class UserController {
    
        @Autowired
        private UserService userService;
    
        
    
        @GetMapping("/")
        public String showHome() {
            return "home";
        }
    
     
        
    
        @GetMapping("/index")
        public String showMenu() {
            return "index";
        }
    
        @GetMapping("/adminMenu")
        public String showadminMenu() {
            return "adminMenu";
        }
    
    
        @GetMapping("/signup")
        public String showSignUpForm() {
            return "signup";
        }
    
        @GetMapping("/contact")
        public String contactForm() {
            return "contact";
        }

        @GetMapping("/login")
public String showLoginPage() {
    return "login"; // This should return the login form view (login.html or JSP)
}


        @GetMapping("/review")
        public String reviewForm() {
            return "review";
        }
        @PostMapping("/login")
        public String loginOrRegister(@RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam(required = false) String formType, 
                                       Model model) {
            try {

                if (username == null || username.trim().isEmpty()) {
                    model.addAttribute("error", "Username is required.");
                    return "login";
                }
                if (password == null || password.trim().isEmpty()) {
                    model.addAttribute("error", "Password is required.");
                    return "login";
                }
                if (password.length() < 6) {
                    model.addAttribute("error", "Password must be at least 6 characters long.");
                    return "login";
                }
                

                // Check if form is for sign-in or sign-up
                if (formType != null && formType.equalsIgnoreCase("signin")) {
                    return handleLogin(username, password, model);
                } else {
                    return handleSignUp(username, password, model);
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "An unexpected error occurred. Please try again.");
                return "login"; // Stay on login page
            }
        }
        
        private String handleLogin(String username, String password, Model model) {
            // Check if user exists
            User user = userService.findByUsername(username);
            if (user == null) {
                model.addAttribute("error", "New user detected. Please complete the signup process first.");
                return "login"; // Stay on login page
            }
        
            if (user.getPassword().equals(password)) {
                // Successful login
                model.addAttribute("username", username);
                return "redirect:/"; // Redirect to the home page
            } else {
                // Incorrect password
                model.addAttribute("error", "Incorrect password. Please try again.");
                return "login";
            }
        }
        
        private String handleSignUp(String username, String password, Model model) {
            // Check if user already exists
            User existingUser = userService.findByUsername(username);
            if (existingUser != null) {
                model.addAttribute("error", "Existing user detected. Please sign in instead.");
                return "login"; // Stay on login page
            }
        
            // Register the new user
            try {
                userService.registerUser(new User(username, password));
                model.addAttribute("message", "Signup successful! Please sign in.");
                return "login"; // Redirect back to login page for user to sign in
            } catch (Exception e) {
                model.addAttribute("error", "An error occurred during signup. Please try again.");
                return "login";
            }
        }
        
     
        @PostMapping("/admin")
        public String loginAdmin(@RequestParam String username, @RequestParam String password, Model model) {
            // Hardcoded credentials for validation
            String hardcodedUsername = "kosurinikitha0747@gmail.com";
            String hardcodedPassword = "Admin@123";
            
            if (username.equals(hardcodedUsername) && password.equals(hardcodedPassword)) {
                model.addAttribute("username", username);
                return "redirect:/adminMenu";  // Redirect to home page if login is successful
            } else {
                model.addAttribute("error2", "Incorrect login details!");  // Error message for invalid credentials
                return "login";  // Stay on login page
            }
        }


    
        @PostMapping("/create_order")
        @ResponseBody
        public String createOrder(@RequestBody Map<String, Object> data) throws Exception {
            try {
                // Ensure the amount is passed correctly
                if (!data.containsKey("amount")) {
                    return "{\"error\":\"Amount is required\"}";
                }
        
                // Parse the amount from the request
                int amt = Integer.parseInt(data.get("amount").toString());
        
                if (amt <= 0) {
                    return "{\"error\":\"Amount must be greater than zero\"}";
                }
        
                // Initialize Razorpay client
                RazorpayClient razorpayClient = new RazorpayClient("rzp_test_znK6IhcaIP67Yd", "f0XrxQesYpqRIHloo0eB8bkf");
        
                // Create order options
                JSONObject options = new JSONObject();
                options.put("amount", amt * 100); // Convert amount to paise (1 INR = 100 paise)
                options.put("currency", "INR");
                options.put("receipt", "txn_123456");
        
                // Create order in Razorpay
                Order order = razorpayClient.Orders.create(options);
        
                // Log the order details (optional)
                System.out.println("Order created successfully: " + order.toString());

              
        
                // Return order details as response
                return order.toString();
        
            } catch (Exception e) {
                e.printStackTrace();
                return "{\"error\":\"Something went wrong while creating the order\"}";
            }
        }
        
    
         @PostMapping("/send_order_to_admin")
    @ResponseBody
    public ResponseEntity<String> sendOrderToAdmin(@RequestBody Map<String, Object> orderDetails) {
        try {
            // Extract the order details from the request
            String orderId = (String) orderDetails.get("orderId");
            Integer amount = (Integer) orderDetails.get("amount");
            String orderInfo = (String) orderDetails.get("orderInfo");

            // You can add logic here to save the order details to a database or process them
            // For example, logging or storing the order details:
            System.out.println("Order ID: " + orderId);
            System.out.println("Amount: " + amount);
            System.out.println("Order Info: " + orderInfo);

            // Send order details to the admin (e.g., send an email, or save in the database)
            // Assuming we have an admin service or method to handle this
            // adminService.sendOrderDetailsToAdmin(orderId, amount, orderInfo);

            // Return a success response
            return ResponseEntity.ok("Order details sent to admin successfully");
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending order details to admin");
        }
    }
      

    
    
    
    }
    