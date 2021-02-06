package com.example.mag.controller;

import com.example.mag.model.Equipment;
import com.example.mag.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Equipment> addNewEquipment(@RequestBody Equipment equipment) {
        return ResponseEntity.ok(equipmentService.save(equipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable Long id)  {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/getByBarCode/{barCode}")
    public ResponseEntity<Equipment> getByBarCode(@PathVariable String barCode) {
        return ResponseEntity.ok(equipmentService.getEquipmentByBarCode(barCode));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateById(@RequestBody Equipment equipment, @PathVariable Long id){
        return ResponseEntity.ok(equipmentService.updateEquipmentById(id, equipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        equipmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assignUser/{userId}/{equipmentId}")
    public ResponseEntity<Equipment> addUserToEquipment(@PathVariable Long userId, @PathVariable Long equipmentId){
        return ResponseEntity.ok(equipmentService.addUserToEquipment(userId,equipmentId));
    }

    @GetMapping("showEquipment/{userId}")
    public ResponseEntity<List<Equipment>> getUserEquipment(@PathVariable Long userId){
        return ResponseEntity.ok(equipmentService.getUserEquipment(userId));
    }
    @PutMapping("/assignStorage/{storageId}/{equipmentId}")
    public ResponseEntity<Equipment> addStorageToEquipment(@PathVariable Long storageId,@PathVariable Long equipmentId){
        return ResponseEntity.ok(equipmentService.addStorageToEquipment(storageId,equipmentId));
    }
}
