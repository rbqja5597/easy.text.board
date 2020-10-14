package com.sbs.example.easytextboard; //폴더명은 소문자로 , 해결책 ctrl + 2 

import java.util.Scanner;

public class Main { // 클래스
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 1번 게시물 저장소
		Article article1 = new Article();

		// 2번 게시물 저장소
		Article article2 = new Article();

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
					article1.id = id;
					article1.title = title;
					article1.body = body;
				} else if (num == 2) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (num == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				if (num >= 1) {
					System.out.printf("%d / %s\n", article1.id, article1.title);
				}
				if (num >= 2) {
					System.out.printf("%d / %s\n", article2.id, article2.title);
				}

			} else if (command.startsWith("article detail ")) {

				int inputedID = Integer.parseInt(command.split(" ")[2]); // 두번째 공백의 값 = inpuedId
				System.out.println("== 게시물 상세 ==");

				if (inputedID == 1) { // 공백의 값이 1일때
					if (article1.id == 0) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedID);
						continue;
					}

					System.out.printf("번호: %d\n", article1.id);
					System.out.printf("제목: %s\n", article1.title);
					System.out.printf("내용: %s\n", article1.body);
				} else if (inputedID == 2) { // 공백의 값이 2일때
					
					if (article2.id == 0) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedID);
						continue;
					}
					
					System.out.printf("번호: %d\n", article2.id);
					System.out.printf("제목: %s\n", article2.title);
					System.out.printf("내용: %s\n", article2.body);
				} else {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedID);
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