package com.liza.newsportal.dao;

import java.util.List;

import com.liza.newsportal.domain.Category;
import com.liza.newsportal.domain.News;
import com.liza.newsportal.domain.Query;

public interface NewsDAO {

	void addNews(News news);

	void addCategory(Category category);

	void editNews(News news);

    News findNews(Integer id);

    Category findCategories(Integer id);

    List<News> listNews();

	List<News> filterListNews(Query query);

	void removeNews(Integer id);

    List<News> listCategories();
}