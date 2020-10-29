package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Shohin;
import com.example.demo.login.domain.repository.ShohinDao;

@Repository("ShohinDaoJdbcImpl")
public class ShohinDaoJdbcImpl implements ShohinDao{
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count(String kigyo_cd) throws DataAccessException{
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM shohinshosai WHERE kigyo_cd = "+kigyo_cd, Integer.class);
		return count;
	}

	@Override
	public int insertOne(Shohin shohin) throws DataAccessException{

		int rowNumber = jdbc.update("INSERT INTO shohin(shohin_id, shohin_name, kigyo_cd, shohin_setsumei, price, shohin_logo) VALUES(?,?,?,?,?,?)"
				,shohin.getShohin_id()
				,shohin.getShohin_name()
				,shohin.getKigyo_cd()
				,shohin.getShohin_setsumei()
				,shohin.getPrice()
				,shohin.getShohin_logo()
				);
		return rowNumber;
	}

	@Override
	public Shohin selectOne(String shohin_id) throws DataAccessException{
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM shohinshosai WHERE shohin_id =?",shohin_id);

		Shohin shohin = new Shohin();
		shohin.setShohin_id((String)map.get("shohin_id"));
		shohin.setShohin_name((String)map.get("shohin_name"));
		shohin.setKigyo_cd((String)map.get("kigyo_cd"));
		shohin.setShohin_setsumei((String)map.get("shohin_setsumei"));
		shohin.setPrice((Integer)map.get("price"));
		shohin.setShohin_logo((String)map.get("shohin_logo"));

		return shohin;
	}
	@Override
	public List<Shohin> selectMany(String kigyo_cd) throws DataAccessException{
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT shohinshosai.shohin_id, shohinshosai.shohin_name, shohinshosai.kigyo_cd, kigyo.kigyo_name, shohinshosai.shohin_setsumei, shohinshosai.price, shohinshosai.shohin_logo FROM shohinshosai LEFT JOIN kigyo ON shohinshosai.kigyo_cd = kigyo.kigyo_cd WHERE shohinshosai.kigyo_cd = ? ",kigyo_cd);

		List<Shohin> shohinList = new ArrayList<>();

		for(Map<String, Object>map:getList) {
			Shohin shohin = new Shohin();

			shohin.setShohin_id((String)map.get("shohin_id"));
			shohin.setShohin_name((String)map.get("shohin_name"));
			shohin.setKigyo_cd((String)map.get("kigyo_cd"));
			shohin.setKigyo_name((String)map.get("kigyo_name"));
			shohin.setShohin_setsumei((String)map.get("shohin_setsumei"));
			shohin.setPrice((Integer)map.get("price"));
			shohin.setShohin_logo((String)map.get("shohin_logo"));

			shohinList.add(shohin);
		}
		return shohinList;
	}

	@Override
	public int updateOne(Shohin shohin) throws DataAccessException{
		int rowNumber = jdbc.update("UPDATE shohin SET shohin_id = ?,"
				+"shohin_name = ?,"
				+"kigyo_cd = ?,"
				+"shohin_setsumei = ?,"
				+"price = ?,"
				+"shohin_logo = ?"
				,shohin.getShohin_id()
				,shohin.getShohin_name()
				,shohin.getKigyo_cd()
				,shohin.getShohin_setsumei()
				,shohin.getPrice()
				,shohin.getShohin_logo());


		return rowNumber;
	}

	@Override
	public int deleteOne(String shohin_id) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM shohin WHERE shohin_id = ?",shohin_id);

		return rowNumber;
	}
}
