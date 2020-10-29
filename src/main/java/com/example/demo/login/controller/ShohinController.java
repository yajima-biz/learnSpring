package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Cart;
import com.example.demo.login.domain.model.Shohin;
import com.example.demo.login.domain.service.CartService;
import com.example.demo.login.domain.service.ShohinService;

@Controller
public class ShohinController {
	@Autowired
	ShohinService shohinService;

	@Autowired
	CartService cartService;


	@GetMapping("/shohinList/{id}")
	public String getShohin(Model model, Pageable pageable, @PathVariable("id") String kigyo_cd) {

		List<Shohin> shohinList = shohinService.selectMany(kigyo_cd);
		int shohinCount = shohinService.count(kigyo_cd);

		model.addAttribute("shohinList",shohinList);
		model.addAttribute("count",shohinCount);

		model.addAttribute("contents","login/shohinList :: shohinList_contents");

		return "login/homelayout";
	}

	@GetMapping("/shohinDetail/{id}")
	public String getShohinShosai(Model model, @PathVariable("id") String shohin_id) {

		Shohin shohin = shohinService.selectOne(shohin_id);

		model.addAttribute("shohin",shohin);

		model.addAttribute("contents","login/shohinDetail :: shohinDetail_contents");

		return "login/homelayout";
	}

	@PostMapping("/shohinInCart")
	public String postShohinInCart(Model model, @ModelAttribute("number") String number, @ModelAttribute("shohin_id") String shohin_id, @ModelAttribute("kigyo_cd") String kigyo_cd) {
		model.addAttribute("contents","login/cart :: cart_contents");
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String login_id = auth.getName();//get logged in username

	    //既存確認
	    int result = cartService.count(login_id, shohin_id);

	    if(result>0) {
	    	Cart cart = cartService.selectOne(login_id,shohin_id);
	    	int oldNumber = cart.getNumber();
	    	int newNumber = oldNumber+Integer.parseInt(number);
	    	cart.setNumber(newNumber);

	    	cartService.updateOne(cart);

	    }else {
			Cart cart = new Cart();
			cart.setShohin_id(shohin_id);
			cart.setLogin_id(login_id);
			cart.setKigyo_cd(kigyo_cd);
			cart.setNumber(Integer.parseInt(number));

			cartService.insertOne(cart);

	    }

		List<Cart> cartList = cartService.selectMany(login_id);

		model.addAttribute("cartList",cartList);

		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String getCart(Model model) {
		model.addAttribute("contents","login/cart :: cart_contents");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login_id = auth.getName();//get logged in username

		List<Cart> cartList = cartService.selectMany(login_id);

		model.addAttribute("cartList",cartList);

		return "login/homelayout";
	}
}
