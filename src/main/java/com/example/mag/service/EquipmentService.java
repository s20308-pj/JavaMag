package com.example.mag.service;

import com.example.mag.exception.CanNotBeEmptyException;
import com.example.mag.exception.EntityExistException;
import com.example.mag.exception.EquipmentNotFoundException;
import com.example.mag.model.Equipment;
import com.example.mag.model.Storage;
import com.example.mag.model.User;
import com.example.mag.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final UserService userService;
    private final StorageService storageService;


    public EquipmentService(EquipmentRepository equipmentRepository, UserService userService, StorageService storageService) {
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
        this.storageService = storageService;
    }

    public Equipment save(Equipment equipment) {
        if (equipment.getName() == null) {
            throw new CanNotBeEmptyException("name");
        }
        if (equipment.getBarCode() == null) {
            throw new CanNotBeEmptyException("name");
        }
//        List<Equipment> equipmentList = getAllEquipment().stream().filter(existEquipment -> existEquipment.getBarCode().equals(equipment.getBarCode())).collect(Collectors.toList());
//        if (!equipmentList.isEmpty()){
//            throw new EntityExistException("BarCode ");
//        }
        return equipmentRepository.save(equipment);
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("equipment ", id));
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentByBarCode(String barCode) {
        return equipmentRepository.findByBarCode(barCode)
                .orElseThrow(() -> new EquipmentNotFoundException(barCode));
    }

    public Equipment updateEquipmentById(Long id, Equipment equipment) {
        Equipment equipmentToUpdate = getEquipmentById(id);
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
        return save(equipmentToUpdate);
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    public Equipment addUserToEquipment(Long userId, Long equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        User user = userService.getUserById(userId);
        if (user != null) {
            equipment.setUserId(user);
        }
        return save(equipment);
    }

    public List<Equipment> getUserEquipment(Long idUser) {
        return getAllEquipment().stream()
                .filter(equipment -> equipment.getUserId() != null)
                .filter(equipment -> equipment.getUserId()
                        .equals(userService.getUserById(idUser)))
                .collect(Collectors.toList());
    }

    public Equipment addStorageToEquipment(Long storageId, Long equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        Storage storage = storageService.getStorageById(storageId);
        if (storage != null) {
            storage.setTime(LocalDate.now());
            storageService.save(storage);
            equipment.setStorageId(storage);
        }
        return save(equipment);
    }

}
