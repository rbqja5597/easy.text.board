package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[10];
	int num = 0;
	int articlesCount = 0;

	Article getArticle(int id) {
		if (id < 1) {
			return null;
		}
		if (id > num) {
			return null;
		}
		return articles[id - 1];

	}

	public void remove(int index) {
		//System.out.printf("== %d번 좌석 숫자 제거 ==\n", index);
		for (int i = index + 1 ; i < articlesCount; i++) {
			articles[i - 1] = articles[i];

		}

		articlesCount--;
	}

	public int getIndexOf(int num) {
		for (int i = 0; i < articles.length; i++) {
			if (num == articles[i].id) {
				return i;
			}
		}
		return -1;
	}


	public void removeByValue(int num) {
		int index = getIndexOf(num);
		if (index != -1) {
			remove(index);
		}
		
		
	}

	public void run() {

		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
		// for (int i=0; i < articles.length; i++) {
		// articles[i].id = i + 1;
		// System.out.printf("articles[%d].id : %d\n", i, articles[i].id);
		// }

		Scanner sc = new Scanner(System.in);

		int maxArticlesCount = articles.length;

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				if (articlesCount >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}

				int id = num + 1; // 0 + 1

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

				num = id; // id의 값은 num과 같다

				Article article = getArticle(num);

				article.id = id;
				article.title = title;
				article.body = body;
				
				articlesCount++;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesCount == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= articlesCount; i++) {

					Article article = getArticle(i);

					System.out.printf("%d / %s\n", article.id, article.title);

				}

				
			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");
				
				Article article = getArticle(inputedId);

				if (article == null|| num < inputedId) { // inputedId 가 num 보다 작으면
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
					continue;
				}

				

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			} else if (command.startsWith("article delete ")) {
				int inp = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inp);
				
				int indexOf = getIndexOf(inp); 
				System.out.printf("indexOf : %d\n", indexOf);
				removeByValue(inp);

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