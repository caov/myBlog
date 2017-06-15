package com.caovan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caovan.dao.BlogCategoryDao;
import com.caovan.dao.BlogDao;
import com.caovan.domain.BlogBean;
import com.caovan.domain.BlogCategoryBean;
import com.caovan.domain.ShareBean;

@Service
public class BlogService {
    
    @Autowired
    private BlogDao blogDao;
    
    @Autowired
    private BlogCategoryDao blogCategoryDao;
    
    public BlogBean addBlog(BlogBean blogBean) {
         return blogDao.save(blogBean);
    }

    public List<BlogCategoryBean> listCategory() {
        return blogCategoryDao.findAll();
    }

    public BlogCategoryBean getBlogCategoryByid(long id) {
        return blogCategoryDao.getOne(id);
    }

    public void updateBlogCategory(BlogCategoryBean blogCategoryBean) {
        blogCategoryDao.save(blogCategoryBean);
    }

    public BlogBean getBlogByid(long id) {
        return blogDao.getOne(id);
    }

    public List<BlogBean> listBlog() {
        return blogDao.findAll();
    }

    public List<BlogBean> listBlogByCId(Long categoryId) {
        return blogDao.findByCategoryId(categoryId);
    }

}
