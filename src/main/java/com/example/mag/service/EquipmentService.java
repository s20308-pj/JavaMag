package com.example.mag.service;

import com.example.mag.entity.Equipment;
import com.example.mag.entity.User;
import com.example.mag.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final UserService userService;


    public EquipmentService(EquipmentRepository equipmentRepository, UserService userService) {
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment " + id + " does not exist"));
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentByBarCode(String tagNumber) {
        return equipmentRepository.findByBarCode(tagNumber)
                .orElseThrow(() -> new EntityNotFoundException("Equipment " + tagNumber + " does not exist"));
    }

    public Equipment updateEquipmentById(Long id, Equipment equipment) {
        Equipment equipmentToUpdate = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment " + id + " does not exist"));
        if (equipment.getImgPatch() != null) {
            equipmentToUpdate.setImgPatch(equipment.getImgPatch());
        }
        if (equipment.getName() != null) {
            equipmentToUpdate.setName(equipment.getName());
        }
        if (equipment.getSerialNumber() != null) {
            equipmentToUpdate.setSerialNumber(equipment.getSerialNumber());
        }
        if (equipment.getBarCode() != null) {
            equipmentToUpdate.setBarCode(equipment.getBarCode());
        }
        return equipmentToUpdate;
    }

    public Equipment addUserToEquipment(Long userId, Long equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        User user = userService.getUserById(userId);
        if (user != null) {
            equipment.setUserId(user);
        }
        return saveEquipment(equipment);
    }

    public List<Equipment> getUserEquipment(Long idUser) {
        return getAllEquipment().stream()
                .filter(equipment -> equipment.getUserId() != null)
                .filter(equipment -> equipment.getUserId()
                        .equals(userService.getUserById(idUser)))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

}
