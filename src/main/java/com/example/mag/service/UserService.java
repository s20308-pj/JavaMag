package com.example.mag.service;

import com.example.mag.entity.User;
import com.example.mag.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByID(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " does not exist"));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public List<User> getUserByName(String nameFrag) {
        return getAllUser().stream()
                .filter(user -> user.getLastName().toLowerCase().contains(nameFrag.toLowerCase()))
                .collect(Collectors.toList());
    }

    public User getUserByUserNumber(Integer userNumber) {
        return userRepository.findByUserNumber(userNumber)
                .orElseThrow(() -> new EntityNotFoundException("User " + userNumber + " does not exist"));
    }

    public User updateUserById(Long id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " does not exist"));
        if (user.getUserNumber() != null) {
            userToUpdate.setUserNumber(user.getUserNumber());
        }
        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (userToUpdate.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }
        return userRepository.save(userToUpdate);
    }

    public void deleteUserById(Long id) {
        User user = getUserByID(id);
        userRepository.delete(user);
    }
}
