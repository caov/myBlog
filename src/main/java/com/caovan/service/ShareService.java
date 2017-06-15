package com.caovan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caovan.dao.ShareCategoryDao;
import com.caovan.dao.ShareDao;
import com.caovan.domain.ShareBean;
import com.caovan.domain.ShareCategoryBean;

@Service
public class ShareService {
    
    @Autowired
    private ShareDao shareDao;
    
    @Autowired
    private ShareCategoryDao shareCategoryDao;

    public List<ShareBean> listShare() {
        return shareDao.findAll();
    }

    public List<ShareCategoryBean> listShareCategory() {
        return shareCategoryDao.findAll();
    }

    public ShareCategoryBean getShareCategoryByid(long categoryId) {
        return shareCategoryDao.getOne(categoryId);
    }

    public List<ShareBean> listShareByCId(Long categoryId) {
        return shareDao.findByCategoryId(categoryId);
    }

    public ShareBean addShare(ShareBean shareBean) {
        return shareDao.save(shareBean);
    }

    public void updateShareCategory(ShareCategoryBean shareCategoryBean) {
        shareCategoryDao.save(shareCategoryBean);
    }
    
    
}
