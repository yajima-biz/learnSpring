package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

@Repository
public interface UserDao {
	public int count() throws DataAccessException;
	public int insertOne(User user) throws DataAccessException;
	public User selectOne(String login_id) throws DataAccessException;
	public List<User> selectMany() throws DataAccessException;
	public int updateOne(User user) throws DataAccessException;
	public int deleteOne(String login_id) throws DataAccessException;
	public void userCsvOut() throws DataAccessException;
}
