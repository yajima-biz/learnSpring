package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class Cart {
	private String login_id;
	private String shohin_id;
	private String kigyo_cd;
	private int number;

	private String shohin_name;
	private String shohin_logo;
	private int price;

}
