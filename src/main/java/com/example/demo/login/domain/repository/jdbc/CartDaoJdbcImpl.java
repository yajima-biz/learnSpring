package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Cart;
import com.example.demo.login.domain.repository.CartDao;

@Repository("CartDaoJdbcImpl")
public class CartDaoJdbcImpl implements CartDao{
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count(String login_id, String shohin_id) throws DataAccessException{
		String where = "shohin_id="+shohin_id+" or NULL";
		int count = jdbc.queryForObject("SELECT COUNT("+where+") FROM (SELECT * FROM cart WHERE login_id = \""+login_id+"\" ) AS login_idcount", Integer.class);
		return count;
	}

	@Override
	public int insertOne(Cart cart) throws DataAccessException{

		int rowNumber = jdbc.update("INSERT INTO cart(login_id, shohin_id, kigyo_cd, number) VALUES(?,?,?,?)"
				,cart.getLogin_id()
				,cart.getShohin_id()
				,cart.getKigyo_cd()
				,cart.getNumber()
				);
		return rowNumber;
	}

	@Override
	public Cart selectOne(String login_id, String shohin_id) {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM cart WHERE login_id =? AND shohin_id = ?",login_id, shohin_id);

		Cart cart = new Cart();
		cart.setLogin_id((String)map.get("login_id"));
		cart.setShohin_id((String)map.get("shohin_id"));
		cart.setKigyo_cd((String)map.get("kigyo_cd"));
		cart.setNumber((int)map.get("number"));

		return cart;
	}

	@Override
	public List<Cart> selectMany(String login_id) throws DataAccessException{
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT shohinshosai.shohin_name, shohinshosai.shohin_logo, shohinshosai.price, cart.number FROM cart LEFT JOIN shohinshosai ON cart.shohin_id = shohinshosai.shohin_id WHERE cart.login_id = ?",login_id);

		List<Cart> cartList = new ArrayList<>();

		for(Map<String, Object>map:getList) {
			Cart cart = new Cart();

			cart.setShohin_name((String)map.get("shohin_name"));
			cart.setShohin_logo((String)map.get("shohin_logo"));
			cart.setPrice((int)map.get("price"));
			cart.setNumber((int)map.get("number"));

			cartList.add(cart);
		}
		return cartList;
	}

	@Override
	public int updateOne(Cart cart) throws DataAccessException{
		int rowNumber = jdbc.update("UPDATE cart SET number = ? WHERE login_id = ? AND shohin_id =?"
				,cart.getNumber()
				,cart.getLogin_id()
				,cart.getShohin_id());


		return rowNumber;
	}

	@Override
	public int deleteOne(String shohin_id, String login_id) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM cart WHERE shohin_id = ? AND login_id = ?",shohin_id,login_id);

		return rowNumber;
	}
}
