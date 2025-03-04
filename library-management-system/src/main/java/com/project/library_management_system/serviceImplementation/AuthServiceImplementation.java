package com.project.library_management_system.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.library_management_system.dto.LoginDTO;
import com.project.library_management_system.repository.userRepository;
import com.project.library_management_system.services.AuthService;

import com.project.library_management_system.entity.userEntity;

@Service
public class AuthServiceImplementation implements AuthService{

    @Autowired
    private userRepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String loginUser(LoginDTO loginDTO) {
        Optional<userEntity> userOpt = userrepository.findByEmail(loginDTO.getEmail());
        if(userOpt.isPresent() && passwordEncoder.matches(loginDTO.getPassword(),userOpt.get().getPassword())){
            return "Login Successfull";
        }
        throw new RuntimeException("Invalid email or password");
        // return "Please try with proper credentials";
        
    }
    
}
