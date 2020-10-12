package com.sbs.example.easytextboard; //폴더명은 소문자로 , 해결책 ctrl + 2 

import java.util.Scanner;

public class Main {     // 클래스 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
		System.out.printf("명령어)");
		String command = scanner.nextLine();
		
		if (command.equals("article list")) {
			System.out.println("== 게시물 리스트 ==");
		} else if (command.equals("article add")) {
			System.out.println("== 게시물 작성 == ");
		} else if (command.equals("system exit")) {
			System.out.println("== 프로그램 종료 == ");
			break;
		} else {
			System.out.println("== 존재하지 않는 명령어 == ");
			}
		}
		scanner.close();
		
	}
}