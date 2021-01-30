package com.example.mag.controller;

import com.example.mag.entity.Storage;
import com.example.mag.service.StorageService;
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
@RequestMapping("/api/storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/save")
    public ResponseEntity<Storage> addNewStorage(@RequestBody Storage storage) {
        return ResponseEntity.ok(storageService.saveStorage(storage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Storage> getStorageById(@PathVariable Long id) {
        return ResponseEntity.ok(storageService.getStorageById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Storage>> getAllStorage() {
        return ResponseEntity.ok(storageService.getAllStorage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Storage> updateStorage(@RequestBody Storage storage, @PathVariable Long id) {
        return ResponseEntity.ok(storageService.updateStorageById(id, storage));
    }

    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        storageService.deleteStorage(id);
    }

    @PutMapping("/assignUser/{userId}/{storageId}")
    public ResponseEntity<Storage> addUserToStorage(@PathVariable Long userId, @PathVariable Long storageId) {
        return ResponseEntity.ok(storageService.addUserToStorage(userId, storageId));
    }
}
