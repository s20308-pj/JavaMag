package com.example.mag.service;

import com.example.mag.model.User;
import com.example.mag.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User(1L, "Adam", "Miałczyński", 213);
        when(userRepository.save(user)).thenReturn(user);
        userRepository.save(user);
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getFirstName()).isEqualTo("Adam");
        assertThat(user.getLastName()).isEqualTo("Miałczyński");
        assertThat(user.getUserNumber()).isEqualTo(213);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Optional<User> equipment = userRepository.findById(1L);
        assertThat(equipment).isNotEmpty();
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(List.of(
                new User("Grzmiwuj", "Bezjajec", 214),
                new User("Mściwuj", "Wolibóg", 215),
                new User("Sędzimir", "Młynarszyk", 216
                )));
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(3);
    }

    @Test
    void deleteUserById() {
        userRepository.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}