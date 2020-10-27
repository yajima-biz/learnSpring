package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Kigyo;
import com.example.demo.login.domain.repository.KigyoDao;

@Repository("KigyoDaoJdbcImpl")
public class KigyoDaoJdbcImpl implements KigyoDao{
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException{
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM kigyo", Integer.class);
		return count;
	}

	@Override
	public int insertOne(Kigyo kigyo) throws DataAccessException{

		int rowNumber = jdbc.update("INSERT INTO kigyo(kigyo_cd, kigyo_name, kigyo_logo) VALUES(?,?,?)"
				,kigyo.getKigyo_cd()
				,kigyo.getKigyo_name()
				,kigyo.getKigyo_logo()
				);
		return rowNumber;
	}

	@Override
	public Kigyo selectOne(String kigyo_cd) throws DataAccessException{
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM kigyo WHERE kigyo_cd =?",kigyo_cd);

		Kigyo kigyo = new Kigyo();
		kigyo.setKigyo_cd((String)map.get("kigyo_cd"));
		kigyo.setKigyo_name((String)map.get("kigyo_name"));
		kigyo.setKigyo_logo((String)map.get("kigyo_logo"));

		return kigyo;
	}
	@Override
	public List<Kigyo> selectMany() throws DataAccessException{
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM kigyo");

		List<Kigyo> kigyoList = new ArrayList<>();

		for(Map<String, Object>map:getList) {
			Kigyo kigyo = new Kigyo();

			kigyo.setKigyo_cd((String)map.get("kigyo_cd"));
			kigyo.setKigyo_name((String)map.get("kigyo_name"));
			kigyo.setKigyo_logo((String)map.get("kigyo_logo"));

			kigyoList.add(kigyo);
		}
		return kigyoList;
	}

	@Override
	public int updateOne(Kigyo kigyo) throws DataAccessException{
		int rowNumber = jdbc.update("UPDATE kigyo SET kigyo_cd = ?,"
				+"kigyo_name = ?,"
				+"kigyo_logo = ?"
				,kigyo.getKigyo_cd()
				,kigyo.getKigyo_name()
				,kigyo.getKigyo_logo());


		return rowNumber;
	}

	@Override
	public int deleteOne(String kigyo_cd) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM kigyo WHERE kigyo_cd = ?",kigyo_cd);

		return rowNumber;
	}
}
