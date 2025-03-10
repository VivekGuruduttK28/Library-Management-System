package com.project.library_management_system.services;

import java.util.List;

import com.project.library_management_system.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface userService extends UserDetailsService {
    String createUser(User user);
    List<User> readUser();
}
