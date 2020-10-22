package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	@Autowired
	private UserService userService;

	private Map<String, String>radioAuth;

	private Map<String, String>initRadioAuth(){

		Map<String, String>radio = new LinkedHashMap<>();

		radio.put("一般", "01");
		radio.put("管理", "02");

		return radio;
	}

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		radioAuth = initRadioAuth();

		model.addAttribute("radioAuth",radioAuth);

		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			return getSignup(form,model);
		}

		System.out.println(form);

		User user = new User();
		user.setLogin_id(form.getLogin_id());
		user.setLogin_password(form.getLogin_password());
		user.setShain_name(form.getShain_name());
		user.setShain_cd(form.getShain_cd());
		user.setShaincategory_cd(form.getShaincategory_cd());

		boolean result = userService.insert(user);

		if(result==true) {
			System.out.println("insert成功");
		}else {
			System.out.println("insert失敗");
		}


		return "redirect:/login";
	}
}
