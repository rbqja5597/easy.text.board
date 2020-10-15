package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	// 1번 게시물 저장소
	Article article1 = new Article();

	// 2번 게시물 저장소
	Article article2 = new Article();

	public Article getArticle(int id) {
		if (id == 1) {
			return article1;
		} else if (id == 2) {
			return article2;
		}
		return null;

	}
	
	public Article getArticle2(int id, String title, String body) {
		if (id == 1) {
			article1.id = id;
			article1.title = title;
			article1.body = body;
		} else if (id == 2) {
			article2.id = id;
			article2.title = title;
			article2.body = body;
		}
		return null;
		
	}
	
	public Article getArticle3(int id) {
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

				System.out.printf("번호: %d\n", id);
				System.out.printf("제목: %s\n", title);
				System.out.printf("내용: %s\n", body);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

				num = id;
				
				
				Article selectedArticle2 = getArticle2(id, title, body);
				
				

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
							
				Article selectedArticle3 = getArticle3(num);
				
				

				if (selectedArticle3 == null || selectedArticle3.id == 0) {
					System.out.printf("%d번 게시물이 없습니다.\n", num);
					continue;
				}				
				System.out.println("번호 / 제목");
				System.out.printf("%d / %s\n", selectedArticle3.id, selectedArticle3.title);
				System.out.printf("%d / %s\n", selectedArticle3.id, selectedArticle3.title);
				
				

			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle == null || selectedArticle.id == 0) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}
				System.out.printf("번호: %d\n", selectedArticle.id);
				System.out.printf("제목: %s\n", selectedArticle.title);
				System.out.printf("내용: %s\n", selectedArticle.body);

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
