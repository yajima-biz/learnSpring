package com.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Kigyo;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.KigyoService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;

	@Autowired
	KigyoService kigyoService;

	private Map<String, String>radioAuth;

	private Map<String, String>initRadioAuth(){

		Map<String, String>radio = new LinkedHashMap<>();

		radio.put("一般", "01");
		radio.put("管理", "02");

		return radio;
	}

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents","login/home :: home_contents");

		List<Kigyo> kigyoList = kigyoService.selectMany();
		model.addAttribute("kigyoList",kigyoList);

		int count = kigyoService.count();
		model.addAttribute("kigyoListCount",count);

		return "login/homeLayout";
	}

	@PostMapping("/logout")
	public String postLogout() {


		return "redirect:/login";
	}

	@GetMapping("/userList")
	public  String getUserList(Model model) {
		model.addAttribute("contents","login/userList :: userList_contents");

		List<User> userList = userService.selectMany();
		model.addAttribute("userList",userList);

		int count = userService.count();
		model.addAttribute("userListCount",count);

		return "login/homeLayout";
	}

	@GetMapping("/userDetail/{id}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String login_id) {
		System.out.println("login_id:"+login_id);

		model.addAttribute("contents","login/userDetail :: userDetail_contents");

		radioAuth = initRadioAuth();

		model.addAttribute("radioAuth",radioAuth);

		if(login_id != null && login_id.length() > 0 ) {
			User user = userService.selectOne(login_id);

			form.setLogin_id(user.getLogin_id());
			form.setLogin_password(user.getLogin_password());
			form.setShain_name(user.getShain_name());
			form.setShain_cd(user.getShain_cd());
			form.setShaincategory_cd(user.getShaincategory_cd());

			model.addAttribute("signupForm",form);
		}

		return "login/homeLayout";

	}

	@PostMapping(value="/userDetail",params="update")
	public String postUserDetatilUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");

		User user = new User();

		user.setLogin_id(form.getLogin_id());
		user.setLogin_password(form.getLogin_password());
		user.setShain_name(form.getShain_name());
		user.setShain_cd(form.getShain_cd());
		user.setShaincategory_cd(form.getShaincategory_cd());

		boolean result = userService.updateOne(user);

		if(result==true) {
			model.addAttribute("result","更新成功");
		}else {
			model.addAttribute("result","更新失敗");
		}

		return getUserList(model);
	}

	@PostMapping(value="/userDetail",params="delete")
	public String postUserDetatilDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");

		boolean result = userService.deleteOne(form.getLogin_id());

		if(result==true) {
			model.addAttribute("result","削除成功");
		}else {
			model.addAttribute("result","削除失敗");
		}

		return getUserList(model);
	}

	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model){
		userService.userCsvOut();

		byte[] bytes = null;

		try {
			bytes = userService.getFile("sample.csv");
		}catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders header = new HttpHeaders();

		header.add("Contet-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename","sample.csv");

		return new ResponseEntity<>(bytes,header,HttpStatus.OK);
	}

	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("content","login/admin :: admin_contents");

		return "login/homeLayout";
	}
}
