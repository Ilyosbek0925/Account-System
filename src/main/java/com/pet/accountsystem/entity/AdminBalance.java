package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AdminBalance extends BaseEntity {

    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
