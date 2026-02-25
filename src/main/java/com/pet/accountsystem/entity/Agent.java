package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Agent extends User {

    @ManyToOne
    @JoinColumn(name = "baza_id")
    private Baza baza;
}