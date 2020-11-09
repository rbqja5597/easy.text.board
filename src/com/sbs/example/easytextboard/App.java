package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.Controller;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;


public class App {
	
	MemberController memberController;
	ArticleController articleController;
	
	public App() {
		
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();
		
		init();
	}

	private void init() {
		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardId = articleService.getDefaultBoardId();
		
	}

	private void makeTestData() {
		// 회원 2명 생성
		MemberService memberService = Container.memberService;
		int firstMemberId = memberService.join("aaa", "aaa", "aaa");
		int secondMemberId = memberService.join("bbb", "bbb", "bbb");
		
		// 공지사항 게시판 작성
		ArticleService articleService = Container.articleService;
		int noticeBoardId= articleService.makeBoard("공지사항");
		
		for (int i= 1; i <= 5; i++) {
			articleService.add(noticeBoardId, firstMemberId, "제목" + i, "내용" + i);
		}
		
		for (int i= 6; i <= 10; i++) {
			articleService.add(noticeBoardId, secondMemberId, "제목" + i, "내용" + i);
		}
		
	}

	public void run() {
		Scanner sc = new Scanner(System.in);

		

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} 
			
			Controller controller = getControllerByCmd(command);
			controller.run(command);
		}

		sc.close();
	}

	private Controller getControllerByCmd(String command) {
		if (command.startsWith("member ")) {
			return memberController;
		} else if (command.startsWith("article ")) {
			return articleController;
		}
		
		return null;
	}
}