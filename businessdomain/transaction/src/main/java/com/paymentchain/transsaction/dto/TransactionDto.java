package com.paymentchain.transsaction.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
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
