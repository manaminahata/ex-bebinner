package com.example.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {
	// Appricationスコープに格納
	@Autowired
	public ServletContext application;
	
	// 入力フォームをブラウザに表示させる
	@RequestMapping("")
	public String index() {
		return "exam03";
	}
	
	// 税抜き価格の表示
	int taxFree = 0;
	
	// 税込価格の表示
	int totalPrice = 0;
	
	// 合計金額を計算
	// 計算結果の出力
	@RequestMapping("/add-price")
	public String addPrice(Integer price1, Integer price2, Integer price3) {
		// applicationスコープに格納
		application.setAttribute("price1", price1);
		application.setAttribute("price1", price2);
		application.setAttribute("price1", price3);
		
		// 税抜き価格の計算
		taxFree = price1 + price2 + price3;
		
		// 税込価格の計算
		totalPrice = (int)(taxFree * 1.1);
		
		// 計算後の金額をapplicationスコープへ格納
		application.setAttribute("taxFree", taxFree);
		application.setAttribute("totalPrice", totalPrice);
		
		// 計算結果の表示
		return "exam03-result";
	}
	
	
	
}
