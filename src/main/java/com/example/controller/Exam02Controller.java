package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam02")
public class Exam02Controller {
	
	// 画面推移があるため、sessionスコープに格納
	@Autowired
	public HttpSession session;
	
	// 足し算フォームを表示する
	@RequestMapping("")
	public String index() {
		return "exam02";
	}
	
	// 足し算用の変数宣言
	int answer = 0;
	
	// 足し算結果画面①を表示する
	@RequestMapping("/result")
	public String addNum(Integer num1, Integer num2) {
		// sessionスコープに名前をつけて格納
		session.setAttribute("num1", num1);
		session.setAttribute("num2", num2);
		
		answer = num1 + num2;
		session.setAttribute("answer", answer);
		
		System.out.println(num1 + "+" + num2 + "=" + answer);
		
		return "exam02-result";
	}
	
	// 引数はいらない
	@RequestMapping("/second")
	public String addNum2() {
		System.out.println(5677);
		return "exam02-result2";
	}
}
