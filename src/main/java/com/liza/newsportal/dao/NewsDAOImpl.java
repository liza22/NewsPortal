package com.liza.newsportal.dao;

import com.liza.newsportal.domain.Category;
import com.liza.newsportal.domain.News;
import com.liza.newsportal.domain.Query;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addNews(News news) {

        sessionFactory.getCurrentSession().save(news);

    }

    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    public void editNews(News news) {
        sessionFactory.getCurrentSession().update(news);
    }

    public News findNews(Integer id) {
        return (News) sessionFactory.getCurrentSession().load(
                News.class, id);
    }

    public Category findCategories(Integer id) {
        return (Category) sessionFactory.getCurrentSession().load(
                Category.class, id);
    }


    @SuppressWarnings("unchecked")
    public List<News> listNews() {

        return sessionFactory.getCurrentSession().createQuery("from News")
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<News> filterListNews(Query query) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(News.class);

        criteria.add(Restrictions.ilike("title", "%" + query.getTitle() + "%"));
        criteria.add(Restrictions.ilike("body", "%" + query.getBody() + "%"));
        if (query.getCategory() != 0)
            criteria.add(Restrictions.eq("category.id", query.getCategory()));

        return criteria.list();
    }


    public void removeNews(Integer id) {
        News news = (News) sessionFactory.getCurrentSession().load(
                News.class, id);
        if (null != news) {
            sessionFactory.getCurrentSession().delete(news);
        }

    }

    @SuppressWarnings("unchecked")
    public List<News> listCategories() {

        return sessionFactory.getCurrentSession().createQuery("from Category")
                .list();
    }
}
