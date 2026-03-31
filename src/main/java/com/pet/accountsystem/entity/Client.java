package com.pet.accountsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client extends BaseEntity {
    private String firstName;
    private String lastName;

    private String status;

    private String groupId;

    private Boolean isActive;

    private String clientType;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "baza_id")
    private Base base;
}