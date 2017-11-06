package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.util.Pager;

import java.util.List;
import java.util.Map;

public interface DeptService {
	List<Map<String, Object>> tree(String pid) ;
	List query(Dept dept);
	Pager queryPage(Dept dept,int pageNum,int pageSize);
	Dept findById(Long id);
	public Dept findByName(String name);
	String add(Dept dto);
	public String batchadd(Dept dto);
	void edit(Dept dto);
	void remove(String[] ids);
	
}
