package com.example.hnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.hnm.dto.CustomerDto;
import com.example.hnm.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void updateIsLoginByusername(String username, Boolean isLogin){
        CustomerDto dto = customerRepository.getCustomerDtobyUsername(username);
        dto.setIsLogin(isLogin);
        customerRepository.save(dto);
    }

    public void joinCustomerDto(CustomerDto dto) {

        // 권한 적용 //
        dto.setRole("USER");
        if(dto.getUsername().equals("root")) {
            dto.setRole("ADMIN");
        }else if(dto.getUsername().equals("manger")){
            dto.setRole("MANAGER");
        }

        // 비밀번호 암호화 적용 //
        String rawPwd = dto.getPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setPassword(encodedPwd);

        dto.setIsLogin(false);

        customerRepository.save(dto);
    }
}
