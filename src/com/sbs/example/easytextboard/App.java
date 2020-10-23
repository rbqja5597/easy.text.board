package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[3];
	int num = 0;
	int articlesCount = 0;

	int articlesSize() {
		return articlesCount;
	}

	public App() {
		articles = new Article[32];
		num = 0;
		articlesCount = 0;

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

	Article getArticle(int id) {
		int index = getIndexOf(id);

		if (index == -1) {
			return null;
		}
		return articles[index];

	}

	public void remove(int id) {
		int index = getIndexOf(id); // ex) id = 1
		if (index == -1) { // index 가 -1 과 같으면
			return; // return
		}
		for (int i = index + 1; i < articlesSize(); i++) { // i = 2 , i 가 articlesSize()보다 작을 경우
			articles[i - 1] = articles[i]; // articles[2 - 1] = articles[i]; = 1

		}

		articlesCount--;
	}

	private boolean isArticlesFull() {
		return articlesCount == articles.length;
	}

	public int getIndexOf(int num) { // ex) 입력된 값 = 1
		for (int i = 0; i < articlesSize(); i++) {
			if (num == articles[i].id) { // num = 1 과 articles[i].id 의 값이 같으면
				return i; // 리턴
			}
		}
		return -1;
	}

	private int add(String title, String body) {

		// 게시물 최대 개수 제한 해제

		if (isArticlesFull()) {
			// System.out.printf("== 배열 사이즈 증가(%d => %d) ==\n", articles.length,
			// articles.length * 2);

			Article[] newArticles = new Article[articles.length * 2];

			for (int i = 0; i < articles.length; i++) {
				newArticles[i] = articles[i];

			}

			articles = newArticles;
		}

		Article article = new Article();

		articles[articlesCount] = article;

		article.id = num + 1;
		article.title = title;
		article.body = body;

		articlesCount++;

		num = article.id;

		return article.id;

	}

	public void run() {

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();

				int id = add(title, body);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			} else if (command.startsWith("article list ")) {
				int page = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 리스트 ==");

				

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				int Inpage = 10;
				int startPos = articlesSize() - 1;
				startPos -= (page - 1) * Inpage ;
				int endPos = startPos - (Inpage - 1);
				
				// 32 23
						
				

				System.out.println("번호 / 제목");

				for (int i = startPos; i >= endPos; i--) {

					Article article = articles[i];

					System.out.printf("%d / %s\n", article.id, article.title);

				}
			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedId);

				if (article == null || num < inputedId) { // inputedId 가 num 보다 작으면
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			} else if (command.startsWith("article delete ")) {
				int inp = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inp);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inp);
					continue;
				}

				remove(inp);

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inp);

			} else if (command.startsWith("article modify ")) {
				int inp = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 수정 ==");

				Article article = getArticle(inp);

				System.out.printf("새 제목: ");
				String title = sc.nextLine();
				System.out.printf("새 내용: ");
				String body = sc.nextLine();

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inp);
					continue;
				}
				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", inp);

			} else if (command.startsWith("article search 1 ")) {
				String st = command.split(" ")[3];
				System.out.println("== 게시물 검색 ==");

				System.out.println("번호 / 제목 / 내용");
				for (int i = 0; i < articlesSize(); i++) {
					Article article = articles[i];
					if ((article.title).contains(st) == true) {
						System.out.printf("%d / %s / %s\n", article.id, article.title, article.body);
					} else if ((article.body).contains(st) == true) {
						System.out.printf("%d / %s / %s\n", article.id, article.title, article.body);
					}

				}

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}
		sc.close();
	}

}