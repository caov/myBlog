package com.caovan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caovan.domain.BlogBean;

public interface BlogDao extends JpaRepository<BlogBean, Long> {

    List<BlogBean> findByCategoryId(Long categoryId);
       
    
}
