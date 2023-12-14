package com.example.hnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hnm.model.dto.HnmDto;
import com.example.hnm.model.repository.HnmRepository;

@Service
public class HnmService {
    
    @Autowired
    private HnmRepository hnmRepository;

    public HnmDto loginCustomer(HnmDto dto){
        HnmDto savedCustomer = hnmRepository.getHnmDtobyCustomerId(dto.getCustomerId());
        return savedCustomer;
    }
}
