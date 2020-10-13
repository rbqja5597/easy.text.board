package com.sbs.example.easytextboard; //폴더명은 소문자로 , 해결책 ctrl + 2 

import java.util.Scanner;

public class Main { // 클래스
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 1번 게시물 저장소
		int article1_id = 0;
		String article1_title = "";

		// 2번 게시물 저장소
		int article2_id = 0;
		String article2_title = "";	

		int lastArticleID = 0;

		while (true) {

			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {

				int id = lastArticleID + 1;

				System.out.println("== 게시물 등록 ==");
				System.out.printf("제목: ");
				String title = scanner.nextLine();
				System.out.printf("내용: ");
				String body = scanner.nextLine();
				
			
				System.out.printf("== %d번 게시물이 생성 되었습니다 == \n", id);
				System.out.printf("번호: %d\n", id);
				System.out.printf("제목: %s\n", title);
				System.out.printf("내용: %s\n", body);

				lastArticleID = id;
				
				if ( lastArticleID == 1 ) {
					article1_id = id;
					article1_title = title;
				} else if ( lastArticleID == 2 ) {
					article2_id = id;
					article2_title = title;
				}

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleID == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}  
				
				if (lastArticleID >= 1) {
					System.out.printf("%d / %s\n", article1_id, article1_title);
				} 
				if (lastArticleID >= 2) {
					System.out.printf("%d / %s\n", article2_id, article2_title);
				}
				

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