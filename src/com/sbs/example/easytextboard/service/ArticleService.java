package com.sbs.example.easytextboard.service;

import java.util.ArrayList;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	
	public int add(int memberId, String title, String body) {
		return articleDao.add(memberId, title, body);
	}
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public int getArticlesSize() {
		return articleDao.getArticlesSize();
	}
	
	public Article getArticleByIndex(int i) {
		return articleDao.getArticleByIndex(i);
	}
	
	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}
	
	public void modify(int inputedId, String title, String body) {
		articleDao.modify(inputedId, title, body);
	}
	
	public void remove(int id) {
		articleDao.remove(id);
	}
}
