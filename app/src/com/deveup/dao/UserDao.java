package com.deveup.dao;

import com.deveup.entity.User;
import com.deveup.util.DeveupConection;

public class UserDao extends DeveupConection<User>{

	public UserDao() {
		super(User.class);
	}
}
