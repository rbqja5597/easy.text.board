package com.sbs.example.easytextboard;

public class Apple {

	public static void main(String[] args) {
		Article[] articles = new Article[9];
		
		
		for (int i=0; i < articles.length; i++ ) {
			articles[i] = new Article();
		}
		
		for (int i=0; i <articles.length; i++) {
			articles[i].id = i + 1;
			System.out.printf("articles[%d].id : %d\n", i , articles[i].id);
		}

	}

}
