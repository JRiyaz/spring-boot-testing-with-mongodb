package com.testing.restapi;

import com.testing.exceptions.UserNotFoundException;
import com.testing.model.User;
import com.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserAPI {

    @Autowired
    private UserService service;

    @PostMapping("add")
    public User saveUser(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("all")
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping({"{id}", "{id}/{email}"})
    public User findById(@PathVariable String id, @PathVariable(required = false) Optional<String> email) {
        if (email.isPresent())
            return service.findByIdAndEmail(id, email.get())
                    .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " and Email: "
                            + email + " not found."));
        else
            return service.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " not found."));
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        final User userEntity = service.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " not found."));

        userEntity.setName(user.getName())
                .setEmail(user.getEmail())
                .setGender(user.getGender());
        return service.save(userEntity);
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        service.deleteById(id);
        return "User with Id: " + id + " deleted successfully";
    }
}
