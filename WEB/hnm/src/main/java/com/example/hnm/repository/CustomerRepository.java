package com.example.hnm.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hnm.dto.CustomerDto;

public interface CustomerRepository extends JpaRepository<CustomerDto,String>{

    @Query(value = "SELECT u.* FROM customers_table u WHERE u.username = :username", nativeQuery= true)
    CustomerDto getCustomerDtobyUsername(@Param(value = "username") String username);
}
