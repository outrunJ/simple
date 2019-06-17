package com.otr.tea.mapper;

import java.util.List;

import com.otr.tea.po.User;
import com.otr.tea.po.UserCustom;
import com.otr.tea.po.UserQueryVo;

public interface UserMapper {
	public User findUserById(int id) throws Exception;

	public User findUserByIdResultMap(int id) throws Exception;
	

	public List<UserCustom> findUserList(UserQueryVo vo) throws Exception;
}
