package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.example.demo.login.domain.model.User;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>>{

	@Override
	public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException{
		List<User> userList = new ArrayList<>();

		while(rs.next()) {
			User user = new User();
			user.setLogin_id(rs.getString("login_id"));
			user.setLogin_password(rs.getString("login_password"));
			user.setShain_name(rs.getString("shain_name"));
			user.setShain_cd(rs.getString("shain_cd"));
			user.setShaincategory_cd(rs.getString("shaincategory_cd"));

			userList.add(user);
		}
		if(userList.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		return userList;
	}

}
