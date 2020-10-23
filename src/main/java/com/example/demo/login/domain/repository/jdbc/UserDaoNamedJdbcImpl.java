package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM user";

		SqlParameterSource params = new MapSqlParameterSource();

		return jdbc.queryForObject(sql, params, Integer.class);
	}

	@Override
	public int insertOne(User user) {
		String sql = "INSERT INTO user(login_id, login_password, shain_name, shain_cd, shaincategory_cd)"
				+"VALUES(:login_id, :login_password, :shain_name, :shain_cd, :shaincategory_cd)";

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("login_id", user.getLogin_id())
				.addValue("login_password", user.getLogin_password())
				.addValue("shain_name", user.getShain_name())
				.addValue("shain_cd", user.getShain_cd())
				.addValue("shaincategory_cd", user.getShaincategory_cd());

		return jdbc.update(sql, params);
	}

	@Override
	public User selectOne(String login_id) {
		String sql = "SELECT * FROM user WHERE login_id = :login_id";

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("login_id", login_id);

		Map<String, Object> map = jdbc.queryForMap(sql, params);

		User user = new User();
		user.setLogin_id((String)map.get("login_id"));
		user.setLogin_password((String)map.get("login_password"));
		user.setShain_name((String)map.get("shain_name"));
		user.setShain_cd((String)map.get("shain_cd"));
		user.setShaincategory_cd((String)map.get("shaincategory_cd"));

		return user;
	}

	@Override
	public List<User> selectMany(){
		String sql = "SELECT * FROM user";

		SqlParameterSource params = new MapSqlParameterSource();

		List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

		List<User> userList = new ArrayList<>();

		for(Map<String, Object> map: getList) {
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
	public int updateOne(User user) {
		String sql = "UPDATE user SET login_id = :login_id,"
				+"login_password = :login_password,"
				+"shain_name = :shain_name,"
				+"shain_cd = :shain_cd,"
				+"shaincategory_cd = :shaincategory_cd"
				+"WHERE login_id = :login_id";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("login_id", user.getLogin_id())
			.addValue("login_password", user.getLogin_password())
			.addValue("shain_name", user.getShain_name())
			.addValue("shain_cd", user.getShain_cd())
			.addValue("shaincategory_cd", user.getShaincategory_cd());

		return jdbc.update(sql, params);
	}

	@Override
	public int deleteOne(String login_id) {
		String sql = "DELETE FROM user WHERE login_id = :login_id";

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("login_id", login_id);

		int rowNumber = jdbc.update(sql, params);

		return rowNumber;
	}

	@Override
	public void userCsvOut() {
		String sql = "SELECT * FROM user";

		UserRowCallbackHandler handler = new UserRowCallbackHandler();

		jdbc.query(sql, handler);
	}

}
