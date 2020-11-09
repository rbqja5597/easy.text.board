package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Board;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	// 가장 상위층 시작
	@Override
	public void run(String command) {
		Scanner sc = new Scanner(System.in);
		
		if (command.equals("article add")) {
			add(sc, command);
		} else if (command.startsWith("article list ")) {
			list(sc, command);
		} else if (command.startsWith("article detail ")) {
			detail(sc, command);
		} else if (command.startsWith("article modify ")) {
			modify(sc, command);
		} else if (command.startsWith("article delete ")) {
			delete(sc, command);
		} else if (command.startsWith("article search ")) {
			search(sc, command);
		} else if (command.equals("article makeBoard")) {
			makeBoard(sc, command);
		} else if (command.startsWith("article selectBoard ")) {
			selectBoard(sc, command);
		} 
		
	}

	private void add(Scanner sc, String command) {
		System.out.println("== 게시물 등록 ==");

		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		String title;
		String body;

		System.out.printf("제목 : ");
		title = sc.nextLine();
		System.out.printf("내용 : ");
		body = sc.nextLine();

		int boardId = Container.session.selectedBoardId;
		int memberId = Container.session.loginedMemberId;


		int id = articleService.add(boardId, memberId, title, body);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

	}

	private void list(Scanner sc, String command) {
		int page = Integer.parseInt(command.split(" ")[2]);
		
		int boardId = Container.session.selectedBoardId;
		Board board = articleService.getBoardById(boardId);
		List<Article> articles = articleService.getForPrintArticles(boardId);

		System.out.printf("== %s 게시판 글 리스트 ==\n", board.name);
		System.out.println("번호 / 작성자 / 제목");

		for (Article article : articles) {
			Member member = memberService.getMemberById(article.memberId);
			System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
		}

	}

	private void detail(Scanner sc, String command) {
		int inputedId = 0;

		try {
			inputedId = Integer.parseInt(command.split(" ")[2]);
		} catch (NumberFormatException e) {
			System.out.println("게시물 번호를 양의 정수로 입력해주세요.");
			return;
		}

		System.out.println("== 게시물 상세 ==");

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);

	}

	private void modify(Scanner sc, String command) {
		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		System.out.println("== 게시물 수정 ==");

		int inputedId = Integer.parseInt(command.split(" ")[2]);
		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.modify(inputedId, title, body);

		System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

	}

	private void delete(Scanner sc, String command) {
		System.out.println("== 게시물 삭제 ==");

		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int inputedId = Integer.parseInt(command.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
			return;
		}

		articleService.remove(inputedId);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

	}

	private void search(Scanner sc, String command) {
		String[] commandBits = command.split(" ");

		String searchKeyword = commandBits[2];

		int page = 1;

		if (commandBits.length >= 4) {
			page = Integer.parseInt(commandBits[3]);
		}

		if (page <= 1) {
			page = 1;
		}

		System.out.println("== 게시물 검색 ==");

		List<Article> searchResultArticles = new ArrayList<>();

		for (Article article : articleService.getArticles()) {
			if (article.title.contains(searchKeyword)) {
				searchResultArticles.add(article);
			}
		}

		if (searchResultArticles.size() == 0) {
			System.out.println("검색결과가 존재하지 않습니다.");
			return;
		}

		System.out.println("번호 / 작성자 / 제목");

		int itemsInAPage = 10;
		int startPos = searchResultArticles.size() - 1;
		startPos -= (page - 1) * itemsInAPage;
		int endPos = startPos - (itemsInAPage - 1);

		if (endPos < 0) {
			endPos = 0;
		}

		if (startPos < 0) {
			System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
			return;
		}

		for (int i = startPos; i >= endPos; i--) {
			Article article = searchResultArticles.get(i);

			System.out.printf("%d / %s / %s\n", article.id, article.memberId, article.title);
		}

	}

	private void makeBoard(Scanner sc, String command) {
		System.out.println("== 게시판 생성 ==");

		System.out.printf("게시판 이름 : ");
		String name = sc.nextLine();

		int BoardId = articleService.makeBoard(name);

		System.out.printf("%s(%d번) 게시판이 생성되었습니다.\n", name, BoardId);

	}

	private void selectBoard(Scanner sc, String command) {
		int boardId = Integer.parseInt(command.split(" ")[2]);

		System.out.println("== 게시판 선택 ==");

		Board board = articleService.getBoardById(boardId);
		
		if (board == null) {
			System.out.printf("%d번 게시판은 존재하지 않습니다.\n", boardId);
			return;
		}

		System.out.printf("%s 게시판이 선택되었습니다.\n", board.name);
		Container.session.selectedBoardId = board.id;
		
	}



}