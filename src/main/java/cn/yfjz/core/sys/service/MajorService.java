package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.util.Pager;

public interface MajorService {
	Pager queryPage(Major major,int pageNum,int pageSize);
	Major findById(Long id);
	String add(Major major);
	void edit(Major major);
	void remove(String[] ids);
}
