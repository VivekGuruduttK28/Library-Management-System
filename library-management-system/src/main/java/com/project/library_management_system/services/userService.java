package com.project.library_management_system.services;

import java.util.List;

import com.project.library_management_system.model.*;
public interface userService {
    String createUser(User user);
    List<User> readUser();
}
