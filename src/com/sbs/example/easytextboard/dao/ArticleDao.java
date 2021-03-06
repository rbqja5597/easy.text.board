package com.sbs.example.easytextboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	private int lastBoard;
	private List<Board> boards;

	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/a2?&useSSL=false&serverTimezone=UTC&jdbcCompliantTruncation=false&user=sbsst&password=sbs123414";

	private final String USER_NAME = "sbsst";
	private final String PASSWORD = "sbs123414";

	Connection conn = null;
	Statement state = null;
	PreparedStatement pstmt;
	ResultSet rs;
	private boolean result;

	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		boards = new ArrayList<>();
		lastBoard = 0;

	}

	public List<Article> getArticles() {
		return articles;
	}

	// 게시물관련 시작
	public Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles.get(index);
	}

	public int add(String nickname, String title, String body) {
		int id = 0;
		
		String insertsql = "INSERT INTO article";
		insertsql +=  "SET title = ?";
		insertsql +=  ",body = ?";
		insertsql +=  ",nickname = ?";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			conn.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setString(3, nickname);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("aaa" + e);
		} catch (SQLException e) {
			System.out.println("aaa" + e);
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (SQLException ex1) {
				System.out.println("Error !");
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex1) {

			}
		}
		return id;
	}

	public Article detail(int inputedId) {
		Article article = null;

		String sql = "SELECT * from article WHERE id=?";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			state = conn.createStatement();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputedId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String number = rs.getString("id");
				String regDate = rs.getString("regDate");
				String titles = rs.getString("title");
				String bodys = rs.getString("body");
				String nickname = rs.getString("nickname");
				String hit = rs.getString("hit");

				article = new Article();

				article.number = number;
				article.regDate = regDate;
				article.titles = titles;
				article.bodys = bodys;
				article.nickname = nickname;
				article.hit = hit;
				articles.add(article);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("aaa" + e);
		} catch (SQLException e) {
			System.out.println("aaa" + e);
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (SQLException ex1) {
				System.out.println("Error !");
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex1) {

			}
		}
		return article;
	}

	public boolean remove(int id) {
		boolean result = false;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			state = conn.createStatement();

			String sql = "DELETE FROM article WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int r = pstmt.executeUpdate();

			pstmt.close();
			state.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("aaa" + e);
		} catch (SQLException e) {
			System.out.println("aaa" + e);
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (SQLException ex1) {
				System.out.println("Error !");
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex1) {

			}
		}
		return result;

	}

	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
				return i;
			}
		}

		return -1;
	}

	public boolean modify(int inputedId, String title, String body) {
		boolean result = false;
		int index = getIndexById(inputedId);

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE article SET ").append("       title=?,").append("       body=?").append("  WHERE id=?");

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			state = conn.createStatement();

			// String sql = "UPDATE article SET title = ? WHERE `body` = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setInt(3, inputedId);

			int r = pstmt.executeUpdate();

			pstmt.close();
			state.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("aaa" + e);
		} catch (SQLException e) {
			System.out.println("aaa" + e);
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (SQLException ex1) {
				System.out.println("Error !");
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex1) {

			}
		}
		return result;
	}

	public int getArticlesSize() {
		return articles.size();
	}

	public Article getArticleByIndex(int i) {
		return articles.get(i);
	}

	public List<Article> getForPrintArticles() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			state = conn.createStatement();

			String sql;
			sql = "SELECT * FROM `article`";
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				String number = rs.getString("id");
				String regDate = rs.getString("regDate");
				String titles = rs.getString("title");
				String bodys = rs.getString("body");
				String nickname = rs.getString("nickname");
				String hit = rs.getString("hit");

				Article article = new Article();

				article.number = number;
				article.regDate = regDate;
				article.titles = titles;
				article.bodys = bodys;
				article.nickname = nickname;
				article.hit = hit;
				articles.add(article);
			}


			rs.close();
			state.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("aaa" + e);
		} catch (SQLException e) {
			System.out.println("aaa" + e);
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (SQLException ex1) {
				System.out.println("Error !");
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex1) {

			}
		}
		return articles;

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
