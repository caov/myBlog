package com.caovan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caovan.domain.UserBean;

public interface UserDao extends JpaRepository<UserBean, Long>{
    
    UserBean findByName(String name);//spring-data-jpa通过解析方法名创建查询
}
