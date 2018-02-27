package com.wd.pydjc.sys.service;

import com.wd.pydjc.common.dto.UserDto;
import com.wd.pydjc.sys.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
