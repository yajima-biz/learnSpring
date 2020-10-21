package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object>findOne(String login_id){
		String query = "SELECT * FROM user WHERE login_id=?";

		Map<String, Object>user = jdbcTemplate.queryForMap(query,login_id);

		return user;

	}
}
