package com.dao;

import java.util.List;
import java.util.Map;

import com.pojo.Rep;

public interface RepMapper {
	public List<Rep> selectByp(Map<String,Object>params);
	public int count(Map<String,Object>params);
	
	public int add(Rep r);
}
