package com.example.hnm.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customers_table")
@Entity(name = "HnmDto")
public class HnmDto {
    
    @Id
    private String customerId;

    @Column
    private int age;
    private String postal_code;
    private String customer_password;
    private String customer_role;
}
