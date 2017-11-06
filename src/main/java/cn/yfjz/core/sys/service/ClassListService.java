package cn.yfjz.core.sys.service;

import java.util.List;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.util.Pager;

public interface ClassListService {
	Pager queryPage(ClassList classList,int pageNum,int pageSize);
	ClassList findById(Long id);
	String add(ClassList classList);
	void edit(ClassList classList);
	void remove(String[] ids);
	List query(ClassList classList);
}
