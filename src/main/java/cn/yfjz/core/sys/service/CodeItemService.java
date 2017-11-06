package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.CodeItem;
		import cn.yfjz.core.util.Pager;

import java.util.List;

public interface CodeItemService {
	Pager queryPageByKind(String kindId,int pageNum,int pageSize);
	CodeItem findById(String id);
	public List<CodeItem> findByKindId(String id);
	public CodeItem findByName(String id);
	void add(CodeItem dto);
	void edit(CodeItem dto);
	void remove(String[] ids);
}
