package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Kigyo;

public interface KigyoDao {
	public int count() throws DataAccessException;
	public int insertOne(Kigyo kigyo) throws DataAccessException;
	public Kigyo selectOne(String Kigyo_cd) throws DataAccessException;
	public List<Kigyo> selectMany() throws DataAccessException;
	public int updateOne(Kigyo kigyo) throws DataAccessException;
	public int deleteOne(String Kigyo_cd) throws DataAccessException;
}
