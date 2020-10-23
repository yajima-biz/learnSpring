package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setLogin_id(rs.getString("login_id"));
		user.setLogin_password(rs.getString("login_password"));
		user.setShain_name(rs.getString("shain_name"));
		user.setShain_cd(rs.getString("shain_cd"));
		user.setShaincategory_cd(rs.getString("shaincategory_cd"));

		return user;
	}

}
