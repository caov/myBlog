package com.caovan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "share_category")
public class ShareCategoryBean {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    private Integer num;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ShareCategoryBean [id=" + id + ", name=" + name + ", num=" + num + "]";
    }
    
    
}
