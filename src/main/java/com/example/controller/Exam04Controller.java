package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	
	// 今回の場合未入力項目があればindexメソッドに戻るように設定する
	@RequestMapping("/result")
	public String result(@Validated UserForm userForm,
			BindingResult result,
			RedirectAttributes redirectAtrributes,
			Model model) {
		
		// 未入力項目があった時の処理（if文は上から処理されるため、一番上に記載すること）
				if (result.hasErrors()) {
					return index();
				}
		
		// ドメインクラスをインスタンス化
		User user = new User();
				
		// userFormオブジェクトからuserオブジェクトへプロパティをコピー
		BeanUtils.copyProperties(userForm, user);
		
		// プロパティが異なるため、手動で値をコピー
		user.setAge(userForm.getIntAge());
		
		// flashスコープにuserの格納（userのなかにageも含まれる）
		redirectAtrributes.addFlashAttribute("user", user);
		
		// 問題なく処理できたら次のメソッドへ
		return "redirect:/exam04/toresult";
	}
	
	@RequestMapping("toresult")
	public String toresult() {
		return "exam04-result";
	}
}
