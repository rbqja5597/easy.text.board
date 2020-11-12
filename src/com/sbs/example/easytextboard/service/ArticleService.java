package com.sbs.example.easytextboard.service;

import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(int boardId, int memberId, String title, String body) {
		return articleDao.add(boardId, memberId, title, body);
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

	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles();
		
	}

	public int makeBoard(String name) {
		return articleDao.makeBoard(name);
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public int getDefaultBoardId() {
		List<Board> boards = articleDao.getBoards();

		return boards.get(0).id;
	}

	public List<Board> getBoardS() {
		return articleDao.getBoards();
	}

	

}
