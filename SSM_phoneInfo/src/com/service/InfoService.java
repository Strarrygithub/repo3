package com.service;

import java.util.Map;

import com.pojo.Info;
import com.util.PageBean;

public interface InfoService {
	public PageBean<Info> selectByP(Map<String,Object>params);
	
	public Info getInfo(Integer id);
	
	public boolean updateInfo(Info i);
}
