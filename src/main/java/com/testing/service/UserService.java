package com.testing.service;

import com.testing.model.User;
import com.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByIdAndEmail(String id, String email) {
        return repository.findByIdAndEmail(id, email);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
