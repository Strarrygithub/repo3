package com.service;

import java.util.Map;

import com.pojo.Rep;
import com.util.PageBean;

public interface RepService {
	public PageBean<Rep> selectByP(Map<String,Object>params);
	
	public boolean add(Rep r);
}
