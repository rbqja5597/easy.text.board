package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	
	public List<Article> getArticles() {
		return articles;
	}

	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		for (int i = 0; i < 32; i++) {
			add(i % 2 ==0 ? 1: 2, "제목" + (i + 1), "내용" + (i + 1));
		}
	}

	// 게시물관련 시작
	public Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles.get(index);
	}

	public int add(int memberId, String title, String body) {

		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.memberId = memberId;
		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	public void remove(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return;
		}

		articles.remove(index);
	}

	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
				return i;
			}
		}

		return -1;
	}

	public void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}
	
	public int getArticlesSize() {
		return articles.size();
	}
	
	public Article getArticleByIndex( int i) {
		return articles.get(i);
	}	
	
}
