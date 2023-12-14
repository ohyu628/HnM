package com.example.hnm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hnm.model.dto.HnmDto;

public interface HnmRepository extends JpaRepository<HnmDto,String>{
    @Query(value = "SELECT u.* FROM customers_table u WHERE u.customer_id = :customerId", nativeQuery= true)
    HnmDto getHnmDtobyCustomerId(@Param(value = "customerId") String customerId);
}
