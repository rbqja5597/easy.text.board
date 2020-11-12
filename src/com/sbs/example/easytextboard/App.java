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
	private ArticleService articleService;
	private MemberService memberService;

	public App() {

		memberController = Container.memberController;
		articleController = Container.articleController;
		
		memberService = Container.memberService;
		articleService = Container.articleService;



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