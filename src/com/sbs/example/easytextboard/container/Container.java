package com.sbs.example.easytextboard.container;

import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.MemberController;
import com.sbs.example.easytextboard.dao.ArticleDao;
import com.sbs.example.easytextboard.dao.MemberDao;
import com.sbs.example.easytextboard.service.ArticleService;
import com.sbs.example.easytextboard.service.MemberService;
import com.sbs.example.easytextboard.session.Session;

public class Container {
	public static Session session;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static MemberController memberController;
	public static ArticleController articleController;
	
	static {
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
	}

}
