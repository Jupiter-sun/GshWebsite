package cn.yfjz.core.sys.service;

import java.util.List;

import cn.yfjz.core.sys.domain.CodeKind;
import cn.yfjz.core.util.Pager;

public interface CodeKindService {
	CodeKind findById(String  id);
	Pager queryPage(String pid, int pageNum, int pageSize);
	void add(CodeKind dto);
	void edit(CodeKind dto);
	void remove(String[] ids);
	public List codeKindTree(String id);

}
