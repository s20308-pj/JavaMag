package com.example.mag.service;

import com.example.mag.exception.CanNotBeEmptyException;
import com.example.mag.exception.UserNotFoundException;
import com.example.mag.model.User;
import com.example.mag.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        if (user.getFirstName() == null) {
            throw new CanNotBeEmptyException("FirstName ");
        }
        if (user.getLastName() == null) {
            throw new CanNotBeEmptyException("LastName");
        }
        if (user.getUserNumber() == null) {
            throw new CanNotBeEmptyException("UserNumber");
        }
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user ", id));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getUserByName(String nameFragmentary) {
        return getAllUser().stream()
                .filter(user -> user.getLastName().toLowerCase().contains(nameFragmentary.toLowerCase()))
                .collect(Collectors.toList());
    }

    public User getUserByUserNumber(Integer userNumber) {
        return userRepository.findByUserNumber(userNumber)
                .orElseThrow(() -> new UserNotFoundException(Integer.toString(userNumber)));
    }

    public User updateUserById(Long id, User user) {
        User userToUpdate = getUserById(id);
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
        userRepository.deleteById(id);
    }
}
