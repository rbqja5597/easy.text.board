package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	private int lastBoard;
	private List<Board> boards;
	
	public List<Article> getArticles() {
		return articles;
	}

	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		
		boards = new ArrayList<>();
		lastBoard =0;	
	}

	// 게시물관련 시작
	public Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles.get(index);
	}

	public int add(int boardId, int memberId, String title, String body) {

		Article article = new Article();
		
		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.memberId = memberId;
		article.boardId = boardId;
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
	
	public Article getArticleByIndex(int i) {
		return articles.get(i);
	}

	public List<Article> getForPrintArticles(int boardId) {
		List<Article> newArticles = new ArrayList<>();

		for (Article article : articles) {
			if (article.boardId == boardId) {
				newArticles.add(article);
			}
		}
		
		
		
		return newArticles;
	}

	public int makeBoard(String name) {
		Board board = new Board();
		
		board.id = lastBoard + 1;
		board.name = name;
		boards.add(board);
		
		lastBoard = board.id;

		return board.id;
	}

	public Board getBoardById(int id) {
		for (Board board : boards) {
			if (board.id == id) {
				return board;
			}
		}
		return null;
	}

	public List<Board> getBoards() {
		return boards;
	}





	
}
