package com.example.mag.service;

import com.example.mag.model.Storage;
import com.example.mag.repository.StorageRepository;
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
class StorageServiceTest {

    @Mock
    private StorageRepository storageRepository;

    @Test
    void saveStorage() {
        Storage storage = new Storage(1L, "Magazyn");
        when(storageRepository.save(storage)).thenReturn(storage);
        storageRepository.save(storage);
        assertThat(storage.getId()).isPositive();
        assertThat(storage.getName()).isEqualTo("Magazyn");
    }

    @Test
    void getStorageById() {
        when(storageRepository.findById(1L)).thenReturn(Optional.of(new Storage()));
        Optional<Storage> storage = storageRepository.findById(1L);
        assertThat(storage).isEmpty();
    }

    @Test
    void getAllStorage() {
        when(storageRepository.findAll()).thenReturn(List.of(
                new Storage("142"),
                new Storage("Magazyn"),
                new Storage("Sztab"),
                new Storage("Port")
        ));
        List<Storage> storageList = storageRepository.findAll();
        assertThat(storageList).hasSize(4);
    }

    @Test
    void deleteStorage() {
        storageRepository.deleteById(1L);
        verify(storageRepository, times(1)).deleteById(1L);
    }
}