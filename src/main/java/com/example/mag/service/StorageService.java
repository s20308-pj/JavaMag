package com.example.mag.service;

import com.example.mag.model.Storage;
import com.example.mag.model.User;
import com.example.mag.repository.StorageRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StorageService {
    private final StorageRepository storageRepository;
    private final UserService userService;

    public StorageService(StorageRepository storageRepository, UserService userService) {
        this.storageRepository = storageRepository;
        this.userService = userService;
    }

    public Storage saveStorage(Storage storage) {
        return storageRepository.save(storage);
    }

    public Storage getStorageById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Storage " + id + " does not exist"));
    }

    public List<Storage> getAllStorage(){
        return storageRepository.findAll();
    }

    public Storage updateStorageById(Long id, Storage storage){
        Storage storageToUpdate = getStorageById(id);
        if (storage.getName() !=null) {
            storageToUpdate.setName(storage.getName());
        }
        return storageToUpdate;
    }

    public void deleteStorage(Long id){
        storageRepository.deleteById(id);
    }

    public Storage addUserToStorage(Long userId, Long storageId){
        Storage storage = getStorageById(storageId);
        User user = userService.getUserById(userId);
        if(user!=null){
            storage.setUserId(user);
        }
        return saveStorage(storage);
    }
}
