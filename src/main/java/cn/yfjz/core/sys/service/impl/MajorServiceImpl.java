package cn.yfjz.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;

import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.sys.service.MajorService;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
@Service
public class MajorServiceImpl implements MajorService{
	@Autowired
	private EbeanServer ebeanServer;

	@Override
	public Pager queryPage(Major major, int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = major.getCondition(major);
        Query<Major> query = QueryUtils.getQuery(Major.class, ebeanServer, "find major", params);
        int count = query.findRowCount();
        OrderBy<Major> orderBy = new OrderBy<Major>("zyh");
        List<Major> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public Major findById(Long id) {
		return ebeanServer.find(Major.class,id);
	}

	@Override
	public String add(Major major) {
		List<Expression> params = new ArrayList<Expression>();
        params.add(new Expression("zyh"," = ",major.getZyh()));
        Query<Major> query = QueryUtils.getQuery(Major.class, ebeanServer, "find major", params);
        int count = query.findRowCount();
        String msg = "";
        if(count > 0){
        	msg = "专业号重复";
        }else{
        	ebeanServer.insert(major);
        }
        return msg;
	}

	@Override
	public void edit(Major major) {
		ebeanServer.update(major);
	}

	@Override
	public void remove(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(Major.class, tempId);
            }
        }
	}
}
