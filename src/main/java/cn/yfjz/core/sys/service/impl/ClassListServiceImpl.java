package cn.yfjz.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.service.ClassListService;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;

@Service
public class ClassListServiceImpl implements ClassListService{
	@Autowired
	private EbeanServer ebeanServer;
	
	@Override
	public Pager queryPage(ClassList classList, int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = classList.getCondition(classList);
        Query<ClassList> query = QueryUtils.getQuery(ClassList.class, ebeanServer, "find classList", params);
        int count = query.findRowCount();
        OrderBy<ClassList> orderBy = new OrderBy<ClassList>("bh");
        List<ClassList> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public ClassList findById(Long id) {
		return ebeanServer.find(ClassList.class,id);
	}

	@Override
	public String add(ClassList classList) {
		List<Expression> params = new ArrayList<Expression>();
        params.add(new Expression("bh"," = ",classList.getBh()));
        Query<ClassList> query = QueryUtils.getQuery(ClassList.class, ebeanServer, "find classList", params);
        int count = query.findRowCount();
        String msg = "";
        if(count > 0){
        	msg = "班号重复";
        }else{
        	ebeanServer.insert(classList);
        }
        return msg;
	}

	@Override
	public void edit(ClassList classList) {
		ebeanServer.update(classList);
	}

	@Override
	public void remove(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(ClassList.class, tempId);
            }
        }
	}

	@Override
	public List query(ClassList classList) {
		List<Expression> params = classList.getCondition(classList);
		Query<ClassList> query = QueryUtils.getQuery(ClassList.class, ebeanServer, "find classList", params);
        OrderBy<ClassList> orderBy = new OrderBy<ClassList>("bh");
        List<ClassList> list = query.setOrder(orderBy).findList();
        return list;
	}

}
