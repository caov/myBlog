package com.caovan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caovan.dao.UserDao;
import com.caovan.domain.UserBean;


@Service
public class UserService {
   
    @Autowired
    private UserDao userDao;
    
    public UserBean getUsersByName(String userName) {
        return userDao.findByName(userName);
    }
}
