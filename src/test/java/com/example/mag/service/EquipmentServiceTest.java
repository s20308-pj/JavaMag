package com.example.mag.service;

import com.example.mag.model.Equipment;
import com.example.mag.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @Mock
    EquipmentRepository equipmentRepository;

    @Test
    void saveEquipment() {
        Equipment equipment = new Equipment(1L, "name", "serialNumber", "imgPatch", "0010101");
        when(equipmentRepository.save(equipment)).thenReturn(equipment);
        equipmentRepository.save(equipment);
        assertThat(equipment.getId()).isPositive();
        assertThat(equipment.getName()).isEqualTo("name");
        assertThat(equipment.getSerialNumber()).isEqualTo("serialNumber");
        assertThat(equipment.getImgPatch()).isEqualTo("imgPatch");
        assertThat(equipment.getBarCode()).isEqualTo("0010101");
    }

    @Test
    void getEquipmentById() {
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(new Equipment()));
        Optional<Equipment> equipment = equipmentRepository.findById(1L);
        assertThat(equipment).isNotEmpty();
    }

    @Test
    void getAllEquipment() {
        when(equipmentRepository.findAll()).thenReturn(List.of(
                new Equipment(1L, "Dell", "123", "img/image1.jpg", "00001"),
                new Equipment(2L, "HP", "12324", "img/image2.jpg", "00002"),
                new Equipment(3L, "Lenovo", "12345", "img/image3.jpg", "00003")
        ));
        List<Equipment> equipmentList = equipmentRepository.findAll();
        assertThat(equipmentList).hasSize(3);
    }

    @Test
    void getEquipmentByBarCode() {
        when(equipmentRepository.findByBarCode("0001")).thenReturn(Optional.of(new Equipment("name", "serialNumber", "imgPatch", "0001")));
        Optional<Equipment> equipment = equipmentRepository.findByBarCode("0001");
        assertThat(equipment).isNotEmpty();
    }

    @Test
    void deleteById() {
        equipmentRepository.deleteById(1L);
        equipmentRepository.deleteById(1L);
        verify(equipmentRepository, times(2)).deleteById(1L);
    }

    @Test
    void addUserToEquipment() {
    }

    @Test
    void getUserEquipment() {
    }

    @Test
    void addStorageToEquipment() {
    }
}