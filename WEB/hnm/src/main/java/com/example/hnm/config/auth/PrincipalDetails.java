package com.example.hnm.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hnm.dto.CustomerDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
    
    private CustomerDto customerDto;

    // 사용자의 권한 리스트를 주입.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            
            @Override
            public String getAuthority() {
                // userDto의 권한이 추가 //
                return customerDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return customerDto.getPassword();
    }

    @Override
    public String getUsername() {
        return customerDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 유무 확인 
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠긴 유무 확인
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 계정 비번 오래 사용했는지 유무 확인 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 활성화된 계정인지 유무 확인  
        return true;
    }
    
}
