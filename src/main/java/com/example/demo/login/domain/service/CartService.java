package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Cart;
import com.example.demo.login.domain.repository.CartDao;


@Service
public class CartService {

	@Autowired
	@Qualifier("CartDaoJdbcImpl")
	CartDao dao;

	public boolean insertOne(Cart cart) {
		int rowNumber = dao.insertOne(cart);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

	public int count(String login_id, String shohin_id) {
		return dao.count(login_id,shohin_id);
	}

	public List<Cart> selectMany(String login_id){
		return dao.selectMany(login_id);
	}

	public Cart selectOne(String login_id, String shohin_id) {
		return dao.selectOne(login_id, shohin_id);
	}

	public boolean updateOne(Cart cart) {
		int rowNumber = dao.updateOne(cart);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

	public boolean deleteOne(String shohin_id, String login_id) {
		int rowNumber = dao.deleteOne(shohin_id,login_id);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

}
