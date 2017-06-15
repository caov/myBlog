package com.caovan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blogCategory")
public class BlogCategoryBean {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    private Integer num;
    
    private String color;
    
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
    
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "BlogCategoryBean [id=" + id + ", name=" + name + ", num=" + num + ",color=" + color + "]";
    }
    
    
}
