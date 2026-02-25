package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Baza extends BaseEntity {

    private String region;
    private String district;

    private String groupId;
}