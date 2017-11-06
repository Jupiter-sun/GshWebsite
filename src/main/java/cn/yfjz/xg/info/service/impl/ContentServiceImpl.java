package cn.yfjz.xg.info.service.impl;

import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.xg.domain.Content;
import cn.yfjz.xg.info.service.ContentService;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuanye
 * @Date：2016年8月23日 
 * @Time：下午3:25:06 
 */
@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private EbeanServer ebeanServer;
    @Override
	public Pager queryPage(Map<String,Object> paramMap,Long pId,int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = new ArrayList<Expression>();
		if(pId == null)
			pId = Long.parseLong(CodeConstant.CHANNEL_ROOT_DEFAULT_ID);
        params.add(new Expression("channel_id","=",pId));
        for (String key : paramMap.keySet()) {
        	params.add(new Expression(key," like ","%"+paramMap.get(key)+"%"));
        }
        Query<Content> query = QueryUtils.getQuery(Content.class, ebeanServer, "find content", params);
        int count = query.findRowCount();
        OrderBy<Content> orderBy = new OrderBy<Content>("topLevel");
        List<Content> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}
	@Override
	public void add(Content dto) {
		ebeanServer.insert(dto);
	}
	@Override
	public Content findById(Long id) {
		return ebeanServer.find(Content.class,id);
	}
	
	@Override
	public void edit(Content dto) {
		ebeanServer.update(dto);
	}
	
	@Override
	public void remove(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(Content.class, tempId);
            }
        }
	}
}
