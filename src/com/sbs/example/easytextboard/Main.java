package com.sbs.example.easytextboard; //폴더명은 소문자로 , 해결책 ctrl + 2 

import java.util.Scanner;

public class Main { // 클래스
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

			
		int article1_id = 0;
		String article1_title = "";
		String article1_body = "";

		int article2_id = 0;
		String article2_title = "";
		String article2_body = "";

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

				// System.out.printf("번호: %d\n", id);
				// System.out.printf("제목: %s\n", title);
				// System.out.printf("내용: %s\n", body);

				// System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

				num = id;

				if (num == 1) {
					article1_id = id;
					article1_title = title;
					article1_body = body;
				} else if (num == 2) {
					article2_id = id;
					article2_title = title;
					article2_body = body;
				}

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (num == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				if (num >= 1) {
					System.out.printf("%d / %s\n", article1_id, article1_title);
				}
				if (num >= 2) {
					System.out.printf("%d / %s\n", article2_id, article2_title);
				}

			} else if (command.startsWith("article detail ")) {
				
				int inpuedID = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");
				
				if (inpuedID == 1 ) {
					System.out.printf("번호: %d\n", article1_id);
					System.out.printf("제목: %s\n", article1_title);
					System.out.printf("내용: %s\n", article1_body);					
				}
				else if (inpuedID == 2 ) {
					System.out.printf("번호: %d\n", article2_id);
					System.out.printf("제목: %s\n", article2_title);
					System.out.printf("내용: %s\n", article2_body);					
				}
				
				if (article1_id == 0 ) {
					System.out.printf("%d번 게시물은 존재하지 않습니다. \n", inpuedID);
					continue;
				}
				

			} else if (command.equals("article exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}
		scanner.close();
	}
}