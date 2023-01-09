package com.paymentchain.transsaction.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTransaction;
    private String reference;
    private String accountIban;
    private LocalDateTime date;
    private double amount;
    private double fee;
    private String description;
    private String status;
    private String channel;

}
