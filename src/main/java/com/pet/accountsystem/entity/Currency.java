package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
public class Currency extends BaseEntity{
    private String usd;
    private LocalDate date;
    private String uzs;
}
