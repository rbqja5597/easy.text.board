package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {
	
	private List<Member> members;
	private int lastMemberId;

	public MemberController() {
		lastMemberId = 0;
		members = new ArrayList<>();
	}
	
	private int join(String loginId, String loginPw, String name) {
		Member member = new Member();
			
		member.id = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		members.add(member);
		lastMemberId = member.id;
		
		return member.id;
	}
	



	public void run(Scanner sc, String command) {
		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdisValid = false;
					
			String loginId = "";
			String loginPw = "";
			String name = "";
			
			
			while (true) {
				
				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("다음에 다시 시도해주세요");
					break;
				}
				
				System.out.printf("로그인 아이디 : ");
				loginId = sc.nextLine().trim();
				
				if(loginId.length() == 0) {
					System.out.println("로그인 아이디를 입력해주세요.");
					loginIdCount++; continue;
				}
				
				if (isJoinableLoginId(loginId) == false ) {
					System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);
					loginIdCount++; break;
				}
				loginIdisValid = true; 
				break;
			}
			if (loginIdisValid == false) {
				return;
			}

			int id = join(loginId, loginPw, name);

			System.out.printf("%d번 회원이 생성되었습니다.\n", id);
		}
	}

	private boolean isJoinableLoginId(String loginId) {
			for (int i =0; i< members.size(); i++) {
				if (members.get(i).loginId.equals(loginId)) {
					return false;
				}
				
			}	
			return true;
	}

}
	
