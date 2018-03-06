package com.liza.newsportal.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Proxy(lazy=false)
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<News> news = new HashSet<News>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<News> getNewsList() {
        return this.news;
    }

    public void setNewsList(Set<News> news) {
        this.news = news;
    }

    public void addNews(News news) {
        news.setCategory(this);
        getNewsList().add(news);
    }

    public void removeNews(News news) {
        getNewsList().remove(news);
    }
}
