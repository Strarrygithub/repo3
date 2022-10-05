package com.dao;

import java.util.List;
import java.util.Map;

import com.pojo.Info;

public interface InfoMapper {
	public List<Info> selectByp(Map<String,Object>params);
	public int count(Map<String,Object>params);
	
	public Info getInfo(Integer id);
	
	public int updateInfo(Info i);
}
