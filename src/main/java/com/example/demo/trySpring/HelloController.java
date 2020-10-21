package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;


	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str, Model model) {
		model.addAttribute("sample",str);

		return "helloResponse";
	}

	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2")String str,Model model) {

		String login_id = str;
		User user = helloService.findOne(login_id);

		model.addAttribute("login_id",user.getLogin_id());
		model.addAttribute("login_password",user.getLogin_password());
		model.addAttribute("shain_name", user.getShain_name());
		model.addAttribute("shain_cd",user.getShain_cd());
		model.addAttribute("shaincategory_cd", user.getShaincategory_cd());

		return "helloResponseDB";

	}
}
