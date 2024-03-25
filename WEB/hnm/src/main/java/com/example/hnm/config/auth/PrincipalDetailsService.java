package com.example.hnm.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hnm.dto.CustomerDto;
import com.example.hnm.repository.CustomerRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        CustomerDto user = customerRepository.getCustomerDtobyUsername(userId);
        if(user != null) {
            // 이미 가입이 된 사용자 // 
            return new PrincipalDetails(user);
        }

        // 가입이 되지 않은 사용자 //
        return null;
    }
}


