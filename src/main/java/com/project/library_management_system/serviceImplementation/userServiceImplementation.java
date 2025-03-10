package com.project.library_management_system.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import com.project.library_management_system.model.UserPrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.library_management_system.entity.userEntity;
import com.project.library_management_system.model.User;
import com.project.library_management_system.repository.userRepository;
import com.project.library_management_system.services.userService;


@Service
public class userServiceImplementation implements userService {

    @Autowired
    userRepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createUser(User user) {
        if(userrepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("User already exists");
            // return "User already exists";
        }
        userEntity userentity = new userEntity();
        BeanUtils.copyProperties(user, userentity);
        userentity.setPassword(passwordEncoder.encode(user.getPassword()));
        userrepository.save(userentity);
        return "Saved Successfully";
    }

    @Override
    public List<User> readUser() {
        
        List<userEntity> userList = userrepository.findAll();
        List<User> users = new ArrayList<>();
        for(userEntity u: userList){
            User user = new User();
            // user.setId(u.getId());
            // user.setName(u.getName());
            // user.setPhone(u.getPhone());
            // user.setRole(u.getRole());
            // user.setEmail(u.getEmail());

            // user.setAddress(u.getAddress());
            // user.setPassword(u.getPassword());
            // users.add(user);

            BeanUtils.copyProperties(u, user);
            users.add(user);
        }
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity user = userrepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));

        return new UserPrincipal(user);
    }
}
