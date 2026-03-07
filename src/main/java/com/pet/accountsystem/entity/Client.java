package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends BaseEntity {

    private String status;

    private String groupId;

    private String clientType;

    @ManyToOne
    @JoinColumn(name = "baza_id")
    private Baza baza;
}