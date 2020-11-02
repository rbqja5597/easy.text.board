package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int lastMemberId;

	public MemberController() {
		lastMemberId = 0;
		members = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			join("user" + i, "user" + i, "유저" + i);
		}
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}

		return null;
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

	private boolean isExistsLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}

		return false;
	}

	private boolean isJoinAvailabelLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}

		return true;
	}

	public void run(Scanner sc, String command) {
		if (command.equals("member login")) {
			System.out.println("== 로그인 ==");

			if (Container.session.isLogined()) {
				System.out.println("이미 로그인 되었습니다.");
				return;
			}

			String loginId = "";
			String loginPw;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;

			Member member = null;

			while (true) {
				if (loginIdMaxCount <= loginIdCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}

				System.out.printf("로그인아이디 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				}

				member = getMemberByLoginId(loginId);

				if (member == null) {
					loginIdCount++;
					System.out.printf("존재하지 않는 로그인아이디 입니다.\n", loginId);
					continue;
				}

				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}

			int loginPwMaxCount = 3;
			int loginPwCount = 0;
			boolean loginPwIsValid = false;

			while (true) {
				if (loginPwMaxCount <= loginPwCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}

				System.out.printf("로그인비번 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					loginPwCount++;
					continue;
				}

				if (member.loginPw.equals(loginPw) == false) {
					loginPwCount++;
					System.out.printf("비밀번호가 일치하지 않습니다.\n");
					continue;
				}

				loginPwIsValid = true;

				break;
			}

			if (loginPwIsValid == false) {
				return;
			}

			System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", member.name);

			Container.session.loginedMemberId = member.id;

		} else if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			String loginId = "";
			String loginPw;
			String name;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;

			while (true) {
				if (loginIdMaxCount <= loginIdCount) {
					System.out.println("회원가입을 취소합니다.");
					break;
				}

				System.out.printf("로그인아이디 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				} else if (isJoinAvailabelLoginId(loginId) == false) {
					loginIdCount++;
					System.out.printf("%s(은)는 이미 사용중인 로그인아이디 입니다.\n", loginId);
					continue;
				}

				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}

			while (true) {
				System.out.printf("로그인비번 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					continue;
				}

				break;
			}

			while (true) {
				System.out.printf("이름 : ");
				name = sc.nextLine().trim();

				if (name.length() == 0) {
					continue;
				}

				break;
			}

			int id = join(loginId, loginPw, name);

			System.out.printf("%d번 회원이 생성되었습니다.\n", id);
		}
	}
}