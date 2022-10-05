package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Info;
import com.pojo.Rep;
import com.service.InfoService;
import com.service.RepService;
import com.util.PageBean;

@Controller
public class PageController {
	
	private Logger logger=Logger.getLogger(PageController.class);
	
	@Autowired
	private InfoService is;
	
	@Autowired
	private RepService rs;
	
	@RequestMapping("toindex")
	public String toindex() {
		return "index";
	}
	
	@RequestMapping("show")
	@ResponseBody
	public Object show(String pageNow) {
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("pageNow", Integer.parseInt(pageNow));
		PageBean<Info>pageBean=is.selectByP(params);	
		map.put("pageBean", pageBean);
		return map;
	}
	
	@RequestMapping("toshowInfo")
	public String toshowInfo(String id) {
		return "showInfo";
	}
	
	@RequestMapping("reshow")
	@ResponseBody
	public Object reshow(String id,String pageNow) {
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>params=new HashMap<String,Object>();
		int _id=Integer.parseInt(id);
		Info i=is.getInfo(_id);
		params.put("pageNow", Integer.parseInt(pageNow));
		PageBean<Rep>pageBean=rs.selectByP(params);	
		map.put("pageBean", pageBean);
		map.put("Info",i);
		return map;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(String infoId,String content) {
		Rep r=new Rep();
		r.setInfoId(Integer.parseInt(infoId));
		r.setReTime(new Date());
		r.setContent(content);
		if(rs.add(r)) {
			return "yes";
		}
		return "no";
	}
	
	@RequestMapping("updateInfo")
	@ResponseBody
	public String updateInfo(String id) {
	Info i=is.getInfo(Integer.parseInt(id));
		int num=i.getvCount();
		i.setvCount(num+1);
		if(is.updateInfo(i)) {
			return "yes";
		}
		return "no";
		
	}
	
}
