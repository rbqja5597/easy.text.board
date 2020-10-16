package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Article article1 = new Article();
	Article article2 = new Article();
	Article article3 = new Article();
	Article article4 = new Article();
	Article article5 = new Article();
	Article article6 = new Article();
	Article article7 = new Article();
	Article article8 = new Article();
	Article article9 = new Article();
	Article article10 = new Article();

	Article getArticle(int id) {
		if (id == 1) {
			return article1;
		} else if (id == 2) {
			return article2;
		}
		return null;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

		int num = 0;

		while (true) {

			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = num + 1;

				System.out.printf("제목: ");
				String title = scanner.nextLine();
				System.out.printf("내용: ");
				String body = scanner.nextLine();

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

				// System.out.printf("번호: %d\n", id);
				// System.out.printf("제목: %s\n", title);
				// System.out.printf("내용: %s\n", body);

				num = id;

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (num == 0) {
					System.out.printf("%d 번 게시물이 없습니다.\n", num);
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= num; i++) {
					Article article = getArticle(i);

					System.out.printf("%d / %s\n", article.id, article.title);

				}

			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				if (num == 0 || inputedId > num) {
					System.out.printf("%d번 게시물이 존재하지 않습니다\n", inputedId);
					continue;
				}

				Article article = getArticle(inputedId);

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목: %s\n", article.title);
				System.out.printf("제목: %s\n", article.body);

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}
		scanner.close();

	}
}
