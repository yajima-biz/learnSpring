package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Cart;

public interface CartDao {
	public int count(String login_id, String shohin_id) throws DataAccessException;
	public int insertOne(Cart cart) throws DataAccessException;
	public Cart selectOne(String login_id, String shohin_id) throws DataAccessException;
	public List<Cart> selectMany(String login_id) throws DataAccessException;
	public int updateOne(Cart cart) throws DataAccessException;
	public int deleteOne(String shohin_id, String login_id) throws DataAccessException;
}
