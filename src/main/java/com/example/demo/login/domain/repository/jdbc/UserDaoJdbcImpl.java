package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao{
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException{
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
		return count;
	}

	@Override
	public int insertOne(User user) throws DataAccessException{

		int rowNumber = jdbc.update("INSERT INTO user(login_id, login_password, shain_name, shain_cd, shaincategory_cd) VALUES(?,?,?,?,?)"
				,user.getLogin_id()
				,user.getLogin_password()
				,user.getShain_name()
				,user.getShain_cd()
				,user.getShaincategory_cd()
				);
		return rowNumber;
	}

	@Override
	public User selectOne(String login_id) throws DataAccessException{
		return null;
	}
	@Override
	public List<User> selectMany() throws DataAccessException{
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user");

		List<User> userList = new ArrayList<>();

		for(Map<String, Object>map:getList) {
			User user = new User();

			user.setLogin_id((String)map.get("login_id"));
			user.setLogin_password((String)map.get("login_password"));
			user.setShain_name((String)map.get("shain_name"));
			user.setShain_cd((String)map.get("shain_cd"));
			user.setShaincategory_cd((String)map.get("shaincategory_cd"));

			userList.add(user);
		}
		return userList;
	}

	@Override
	public int updateOne(User user) throws DataAccessException{
		return 0;
	}

	@Override
	public int deleteOne(String login_id) throws DataAccessException{
		return 0;
	}

	@Override
	public void userCsvOut() throws DataAccessException{

	}
}
