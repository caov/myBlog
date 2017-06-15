package com.caovan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class BlogBean {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private long categoryId;
    
    @Column(nullable = false)
    private Date datetime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "BlogBean [id=" + id + ", name=" + name + ", content=" + content + ", categoryId=" + categoryId + ", datetime=" + datetime
                + "]";
    }
    
}
