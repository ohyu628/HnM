package com.example.hnm.dto;

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
@Entity(name = "CustomerDto")
public class CustomerDto {
    @Id
    private String username;    // 원래는 customerId
    
    @Column
    private int age;
    private String address;
    private String password;
    private String role;

    // 로그인 유무 //
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;

}
