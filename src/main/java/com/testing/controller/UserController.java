package com.testing.controller;

import com.testing.model.User;
import com.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("add")
    public User saveUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("all")
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable String id) {
        return repository.findById(id).get();
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        final User userEntity = repository.findById(id).get();
        userEntity.setName(user.getName())
                .setEmail(user.getEmail())
                .setGender(user.getGender());
        return repository.save(userEntity);
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        repository.deleteById(id);
        return "User with Id: " + id + " deleted successfully";
    }
}
