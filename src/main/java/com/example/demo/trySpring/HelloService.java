package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	@Autowired
	private HelloRepository helloRepository;

	public User findOne(String login_id) {
		Map<String,Object>map=helloRepository.findOne(login_id);

		String newlogin_id = (String)map.get("login_id");
		String login_password = (String)map.get("login_password");
		String shain_name = (String)map.get("shain_name");
		String shain_cd = (String)map.get("shain_cd");
		String shaincategory_cd =(String) map.get("shaincategory_cd");

		User user = new User();
		user.setLogin_id(newlogin_id);
		user.setLogin_password(login_password);
		user.setShain_cd(shain_cd);
		user.setShain_name(shain_name);
		user.setShaincategory_cd(shaincategory_cd);

		return user;
	}


}
