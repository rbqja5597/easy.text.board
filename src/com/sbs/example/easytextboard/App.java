package com.sbs.example.easytextboard;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	ArrayList<Article> list = new ArrayList<Article>();
	int num = 0;
	int articlesCount = 0;
	int id;

	int articlesSize() {
		return articlesCount;
	}

	

	public void run() {
		
		for (int i = 0; i < 32; i++) {
			list.add(new Article(i+1,"제목" + (i + 1), "내용" + (i + 1)));
		}
		
		

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");
				
				id = num + 1;

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();

				num = num + 1;
				
				
				list.add(new Article(id, title, body));
				
				articlesCount++;
				
				System.out.printf("%d번 게시물이 생성되었습니다.\n", num);

			} else if (command.equals("article list")) {
				//int page = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 리스트 ==");
				
				System.out.println("번호 / 제목");

				for (Article list1 : list) {
					System.out.printf("%d / %s\n", list1.id, list1.title);
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

