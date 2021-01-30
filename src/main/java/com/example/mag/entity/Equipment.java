package com.example.mag.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String serialNumber;
    private String imgPatch;
    @NotBlank
    private String barCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storageId;

    public Equipment() {
    }

    public Equipment(Long id, String name, String serialNumber, String imgPatch, String barCode) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.imgPatch = imgPatch;
        this.barCode = barCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getImgPatch() {
        return imgPatch;
    }

    public void setImgPatch(String imgPatch) {
        this.imgPatch = imgPatch;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String tagNumber) {
        this.barCode = tagNumber;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Storage getStorageId() {
        return storageId;
    }

    public void setStorageId(Storage storage) {
        this.storageId = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id)
                && Objects.equals(name, equipment.name)
                && Objects.equals(serialNumber, equipment.serialNumber)
                && Objects.equals(imgPatch, equipment.imgPatch)
                && Objects.equals(barCode, equipment.barCode)
                && Objects.equals(userId, equipment.userId)
                && Objects.equals(storageId, equipment.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, serialNumber, imgPatch, barCode, userId, storageId);
    }
}