package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Shohin;
import com.example.demo.login.domain.repository.ShohinDao;


@Service
public class ShohinService {

	@Autowired
	@Qualifier("ShohinDaoJdbcImpl")
	ShohinDao dao;

	public boolean insert(Shohin shohin) {
		int rowNumber = dao.insertOne(shohin);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

	public int count(String kigyo_cd) {
		return dao.count(kigyo_cd);
	}

	public List<Shohin> selectMany(String kigyo_cd){
		return dao.selectMany(kigyo_cd);
	}

	public Shohin selectOne(String login_id) {
		return dao.selectOne(login_id);
	}

	public boolean updateOne(Shohin shohin) {
		int rowNumber = dao.updateOne(shohin);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

	public boolean deleteOne(String login_id) {
		int rowNumber = dao.deleteOne(login_id);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

}
