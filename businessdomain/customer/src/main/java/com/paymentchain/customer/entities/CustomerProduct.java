package com.paymentchain.customer.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class CustomerProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long productId;
    @Transient
    private String productName;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name="customerId",nullable = false)
    private Customer customer;

}
