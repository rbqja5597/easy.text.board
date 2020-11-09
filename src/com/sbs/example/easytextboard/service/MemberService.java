package com.sbs.example.easytextboard.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}
	
	
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	private boolean isExistsLoginId(String loginId) {
		return memberDao.isExistsLoginId(loginId);
	}

	public boolean isJoinAvailabelLoginId(String loginId) {
		return memberDao.isJoinAvailabelLoginId(loginId);
}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

}