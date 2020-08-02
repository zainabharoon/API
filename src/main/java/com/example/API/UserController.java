package com.example.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getAllEmployeeById(@PathVariable(value="userId") String userId) {
        return userRepository.findById(userId);
    }

}
