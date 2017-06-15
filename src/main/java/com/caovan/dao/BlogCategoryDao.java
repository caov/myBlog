package com.caovan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caovan.domain.BlogCategoryBean;

public interface BlogCategoryDao extends JpaRepository<BlogCategoryBean, Long>{

}
