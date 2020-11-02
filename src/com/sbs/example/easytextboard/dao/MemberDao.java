package com.sbs.example.easytextboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;
	

	public MemberDao() {
		lastMemberId = 0;
		members = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			join("user" + i, "user" + i, "유저" + i);
		}
	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}

		return null;
	}
	
	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();

		member.id = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		members.add(member);
		lastMemberId = member.id;

		return member.id;
	}
	
	public boolean isExistsLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean isJoinAvailabelLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}

		return true;
	}
	
}
