package com.cp.kku.housely.service;

import com.cp.kku.housely.model.Users;
import com.cp.kku.housely.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Users not found"));
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Users not found"));
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

}