package com.example.demo.login.domain.repository.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class UserRowCallbackHandler implements RowCallbackHandler{
	@Override
	public void processRow(ResultSet rs) throws SQLException{
		try {

			File file = new File("sample.csv");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			do {
				String str = rs.getString("login_id")+","
						+ rs.getString("login_password") + ","
						+ rs.getString("shain_name") + ","
						+ rs.getString("shain_cd") + ","
						+ rs.getString("shaincategory_cd");

				bw.write(str);
				bw.newLine();
			}while(rs.next());

			bw.flush();
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}
