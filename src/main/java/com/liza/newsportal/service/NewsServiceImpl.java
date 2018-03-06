package com.liza.newsportal.service;

import java.util.List;

import com.liza.newsportal.domain.Category;
import com.liza.newsportal.domain.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liza.newsportal.dao.NewsDAO;
import com.liza.newsportal.domain.News;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Transactional
    public void addNews(News news) {
        newsDAO.addNews(news);
    }

    @Transactional
    public void addCategory(Category category) {newsDAO.addCategory(category);}


    @Transactional
    public void editNews(News news) {
        newsDAO.editNews(news);
    }

    @Transactional
    public News findNews(Integer id) {
        return newsDAO.findNews(id);
    }

    @Transactional
    public Category findCategories(Integer id) {
        return newsDAO.findCategories(id);
    }

    @Transactional
    public List<News> listNews() {

        return newsDAO.listNews();
    }

    @Transactional
    public List<News> filterListNews(Query query) {

        return newsDAO.filterListNews(query);
    }


    @Transactional
    public void removeNews(Integer id) {
        newsDAO.removeNews(id);
    }

    @Transactional
    public List<News> listCategories() {
        return newsDAO.listCategories();
    }
}
