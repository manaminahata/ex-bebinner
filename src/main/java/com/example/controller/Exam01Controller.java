package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Exam01;
import com.example.form.Exam01Form;

@Controller
@RequestMapping("/exam01")
public class Exam01Controller {
	// formをセットするrequestスコープを作成
	@ModelAttribute
	public Exam01Form setUpForm() {
		return new Exam01Form();
	}
	
	// Webブラウザにフォームを表示させる
	@RequestMapping("")
	public String index() {
		return "exam01";
	}
	
	// Webブラウザに入力後の画面を表示させる
	@RequestMapping("/result")
	public String result(Exam01Form form, Model model) {
		Exam01 exam01 = new Exam01();
		exam01.setName(form.getName());
		model.addAttribute("exam01", exam01);
		return "exam01-result";
	}
}
