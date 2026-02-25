package com.pet.accountsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransactionInkassa extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private String usdAmount;
    private String uzsAmount;
    private String clickAmount;
    private String bankAmount;

    private String description;
}
