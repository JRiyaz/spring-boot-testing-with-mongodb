package com.testing.service;

import com.testing.model.File;
import com.testing.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileRepository repository;

    public File save(File file) {
        return repository.save(file);
    }

    public Optional<File> findById(String id) {
        return repository.findById(id);
    }

    public List<File> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
