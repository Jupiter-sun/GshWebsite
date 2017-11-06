package cn.yfjz.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.yfjz.core.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;

import cn.yfjz.core.sys.domain.CodeItem;
import cn.yfjz.core.sys.service.CodeItemService;

@Service
public class CodeItemServiceImpl implements CodeItemService{
	@Autowired
	private EbeanServer ebeanServer;

	@Override
	public Pager queryPageByKind(String kindId, int pageNum,int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = new ArrayList<Expression>();
		params.add(new Expression("kind_id", " = ", kindId));
		Query<CodeItem> query = QueryUtils.getQuery(CodeItem.class, ebeanServer, "find codeItem", params);
		int count = query.findRowCount();
		OrderBy<CodeItem> orderBy = new OrderBy<CodeItem>("orderNo");
		List<CodeItem> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
		page.setRecords(count);
		page.setRows(list);
		return page;
	}

	@Override
	public CodeItem findById(String id) {
		return ebeanServer.find(CodeItem.class,id);
	}

	@Override
	public List<CodeItem> findByKindId(String id) {
		return ebeanServer.find(CodeItem.class).where().eq("kind_id",id).findList();
	}

	@Override
	public CodeItem findByName(String name) {
		return ebeanServer.find(CodeItem.class).where().eq("item_name",name).findUnique();
	}

	@Override
	public void add(CodeItem dto) {
		ebeanServer.insert(dto);
	}

	@Override
	public void edit(CodeItem dto) {
		ebeanServer.update(dto);
	}

	@Override
	public void remove(String[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (String id : ids) {
				ebeanServer.delete(CodeItem.class, id);
			}
		}

	}
}
