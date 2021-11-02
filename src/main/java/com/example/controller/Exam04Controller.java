package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.User;
import com.example.form.UserForm;

@Controller
@RequestMapping("exam04")
public class Exam04Controller {
	// バリデーションを使う際はModelオブジェクトが必須
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	// 入力フォームをWebブラウザに表示する
	@RequestMapping("")
	public String index() {
		return "exam04";
	}
	
	// 入力画面の出力
	// 今回の場合未入力項目があればindexメソッドに戻るように設定する
	@RequestMapping("/result")
	public String result(UserForm userForm,
			BindingResult result,
			RedirectAttributes redirectAtrributes,
			Model model) {
		// ドメインクラスをインスタンス化
		User user = new User();
		
		// userFormオブジェクトからuserオブジェクトへプロパティをコピー
		BeanUtils.copyProperties(userForm, user);
		
		// flashスコープにuserの格納
		redirectAtrributes.addAttribute("user", user);
		
		// 未入力項目があった時の処理
		if (result.hasErrors()) {
			return "index()";
		}
		
		// 問題なく処理できたら下記をリターン
		return "exam04-result";
	}
}
