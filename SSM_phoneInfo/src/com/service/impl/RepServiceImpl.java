package com.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RepMapper;
import com.pojo.Info;
import com.pojo.Rep;
import com.service.InfoService;
import com.service.RepService;
import com.util.PageBean;

@Service
public class RepServiceImpl implements RepService {
	@Autowired
	private RepMapper rm;
	
	@Autowired
	private InfoService is;
	@Override
	public PageBean<Rep> selectByP(Map<String, Object> params) {
		PageBean<Rep>pageBean=new PageBean<Rep>();
		int pageNow=1;
		if(params.containsKey("pageNow")) {
			pageNow=(int) params.get("pageNow");
		}
		pageBean.setPageNow(pageNow);
		pageBean.setPageSize(3);
		params.put("size", pageBean.getPageSize());
		params.put("start", (pageBean.getPageNow()-1)*pageBean.getPageSize());
		pageBean.setList(rm.selectByp(params));
		pageBean.setTotal(rm.count(params));
		return pageBean;
	}
	@Override
	@Transactional
	public boolean add(Rep r) {
		if(rm.add(r)==1) {
			Info in=is.getInfo(r.getInfoId());
			in.setLast(new Date());
			int num=in.getReCount();
			in.setReCount(num+1);
			if(!is.updateInfo(in)) {
				throw new RuntimeException("修改异常");
			}
			return true;
		}
		throw new RuntimeException("添加异常");
	}

	
}
