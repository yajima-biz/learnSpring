package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Kigyo;
import com.example.demo.login.domain.repository.KigyoDao;

@Service
public class KigyoService {
	@Autowired
	@Qualifier("KigyoDaoJdbcImpl")
	KigyoDao dao;

	public boolean insert(Kigyo kigyo) {
		int rowNumber = dao.insertOne(kigyo);

		boolean result = false;

		if(rowNumber>0) {
			result = true;
		}

		return result;
	}

	public int count() {
		return dao.count();
	}

	public List<Kigyo> selectMany(){
		return dao.selectMany();
	}

	public Kigyo selectOne(String login_id) {
		return dao.selectOne(login_id);
	}

	public boolean updateOne(Kigyo kigyo) {
		int rowNumber = dao.updateOne(kigyo);

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
