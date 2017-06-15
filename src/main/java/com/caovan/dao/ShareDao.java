package com.caovan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caovan.domain.ShareBean;

public interface ShareDao extends JpaRepository<ShareBean, Long>{

    List<ShareBean> findByCategoryId(Long categoryId);
    
}
