package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Shohin;

public interface ShohinDao {
	public int count() throws DataAccessException;
	public int insertOne(Shohin shohin) throws DataAccessException;
	public Shohin selectOne(String shohin_id) throws DataAccessException;
	public List<Shohin> selectMany() throws DataAccessException;
	public int updateOne(Shohin shohin) throws DataAccessException;
	public int deleteOne(String shohin_id) throws DataAccessException;
}
