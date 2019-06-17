package com.otr.tea.po;

import java.util.List;

/**
 * Vo 指视图层对象, Po指持久层对象
 * 
 * @author outrun
 *
 */
public class UserQueryVo {
	
	private List<Integer> ids;
	
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	

}
