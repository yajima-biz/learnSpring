package com.example.demo.login.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(groups=ValidGroup1.class)
	@Length(max=10,groups=ValidGroup2.class)
	@Pattern(regexp="^[a-zA-Z0-9]+$",groups=ValidGroup3.class)
	private String login_id;

	@NotBlank(groups=ValidGroup1.class)
	@Length(max=10, groups=ValidGroup2.class)
	@Pattern(regexp="^[a-zA-Z0-9]+$", groups=ValidGroup3.class)
	private String login_password;

	@NotBlank(groups=ValidGroup1.class)
	@Length(max=10, groups=ValidGroup2.class)
	private String shain_name;

	@NotBlank(groups=ValidGroup1.class)
	@Length(max=3, groups=ValidGroup2.class)
	@Pattern(regexp="^[0-9]+$", groups=ValidGroup3.class)
	private String shain_cd;

	@NotBlank(groups=ValidGroup1.class)
	private String shaincategory_cd = "01";
}
