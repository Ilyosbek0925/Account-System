package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bases")
public class Base extends BaseEntity {

    private String region;
    private String district;
    private String groupId;
}