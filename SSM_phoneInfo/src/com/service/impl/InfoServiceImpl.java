package com.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InfoMapper;
import com.pojo.Info;
import com.service.InfoService;
import com.util.PageBean;

@Service
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoMapper im;
	@Override
	public PageBean<Info> selectByP(Map<String, Object> params) {
		PageBean<Info>pageBean=new PageBean<Info>();
		int pageNow=1;
		if(params.containsKey("pageNow")) {
			pageNow=(int) params.get("pageNow");
		}
		pageBean.setPageNow(pageNow);
		pageBean.setPageSize(5);
		
		params.put("size", pageBean.getPageSize());
		params.put("start", (pageBean.getPageNow()-1)*pageBean.getPageSize());
		pageBean.setList(im.selectByp(params));
		pageBean.setTotal(im.count(params));
		return pageBean;
	}
	@Override
	public Info getInfo(Integer id) {
		return im.getInfo(id);
	}
	@Override
	public boolean updateInfo(Info i) {
		if(im.updateInfo(i)==1) {
			return true;
		}
		return false;
	}

}
