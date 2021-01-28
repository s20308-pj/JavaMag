package com.example.mag.service;

import com.example.mag.entity.Equipment;
import com.example.mag.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public Equipment getEquipmentByID(Long id) {
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

    public Equipment addUserToEquipment(long userId, Long equipmentId){
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Equipment " + equipmentId + " does not exist"));
        if (userService.getUserByID(userId) != null){
            equipment.setUserId(userService.getUserByID(userId));
        }
        return equipment;
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

}
