package com.example.service;

import com.example.dto.UserEnrollDTO;
import com.example.dto.UserLoginDTO;
import com.example.entity.User;

import java.util.List;

public interface UserService {

    User login(UserLoginDTO userLoginDTO) ;

    User getById(Long id);

    Boolean enroll(UserEnrollDTO userEnrollDTO);

    List<User> list();
    void delete(Integer id);
}
