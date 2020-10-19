package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[100]; // 0 ~ 9
	
	Article getArticle(int id) {
		if ( id >= articles.length ) { 
			return null;
		}
		//System.out.println(id);
		return articles[id - 1];
		
	}
	

	public void run() {
		
		for (int i=0; i < articles.length; i++) {
			articles[i] = new Article();
		}
			
		Scanner sc = new Scanner(System.in);

		int num = 0; 

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = num + 1; // 0 + 1 

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("제목: ");
				String body = sc.nextLine();

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

				num = id; // id의 값은 num과 같다  

				Article article = getArticle(num);

				article.id = id;
				article.title = title;
				article.body = body;
				
				

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (num == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
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

				if (num == 0 || num < inputedId) { //inputedId 가 num 보다 작으면
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
					continue;
				}

				Article article = getArticle(inputedId);

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

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
