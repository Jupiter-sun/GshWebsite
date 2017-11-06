package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.sys.service.DeptService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author liuy
 *
 */
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
    private EbeanServer ebeanServer;
	
	@Override
	public List<Map<String, Object>> tree(String pId) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		if(StringUtils.isEmpty(pId)){
			Dept root = ebeanServer.find(Dept.class, 1);
			Map<String,Object> rootMap = new HashMap<String,Object>();
			if(null!=root){
				rootMap.put("id", root.getId());
				rootMap.put("name", root.getName());
			}else{
				rootMap.put("id",CodeConstant.DEPT_ROOT_DEFAULT_ID);
				rootMap.put("name",CodeConstant.DEPT_ROOT_DEFAULT_NAME);
			}
			rootMap.put("isParent", "true");
			resultList.add(rootMap);
		}else{
			List<Expression> params = new ArrayList<Expression>();
            params.add(new Expression("father_id","=",pId));
            params.add(new Expression("status", "=", "1"));
            Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
            OrderBy<Dept> orderBy = new OrderBy<Dept>("orderNumber");
            List<Dept> children = query.setOrder(orderBy).findList();
            if(children!=null && children.size()>0){
				for(Dept child : children){
					resultList.add(changeToMap(child));
				}
			}
		}
		return resultList;
	}
	
	private Map<String,Object> changeToMap(Dept dept){
		Map<String,Object> deptMap=new HashMap<String,Object>();
		deptMap.put("id", dept.getId());
		deptMap.put("name", dept.getName());
		deptMap.put("pId", dept.getFather().getId());
		//deptMap.put("iconSkin", "deptChild");
		List<Expression> params = new ArrayList<Expression>();
		params.add(new Expression("father_id","=",dept.getId()));
		Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
        int count = query.findRowCount();
		if(count>0){
			deptMap.put("isParent", "true");
			//deptMap.put("iconSkin", "deptRoot");
		}
		return deptMap;
	}
	
	@Override
	public List query(Dept dept) {
		List<Expression> params = dept.getCondition(dept);
		Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
        OrderBy<Dept> orderBy = new OrderBy<Dept>("code");
        List<Dept> list = query.setOrder(orderBy).findList();
        return list;
	}

	@Override
	public Pager queryPage(Dept dept,int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = dept.getCondition(dept);
		Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
		int count = query.findRowCount();
		OrderBy<Dept> orderBy = new OrderBy<Dept>("orderNumber");
		List<Dept> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).orderBy("id desc").findList();
		if(list.size() == 0){
			params = new ArrayList<Expression>();
			params.add(new Expression(" id "," = ",dept.getId()));
			Query<Dept> query2 = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept",params);
			int count2 = query2.findRowCount();
			List<Dept> list2 = query2.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
			page.setRecords(count2);
			page.setRows(list2);
			return page;
		}
		page.setRecords(count);
		page.setRows(list);
		return page;
	}

	@Override
	public Dept findById(Long id) {
		return ebeanServer.find(Dept.class,id);
	}

	@Override
	public Dept findByName(String name) {
		return ebeanServer.find(Dept.class).where().eq("name",name).findUnique();
	}

	@Override
	public String add(Dept dto) {
		List<Expression> params = new ArrayList<Expression>();
        params.add(new Expression("code","=",dto.getCode()));
        Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
        int count = query.findRowCount();
        String msg = "";
        if(count > 0){
        	msg = "部门编号重复";
        }else{
        	ebeanServer.insert(dto);
        }
        return msg;
	}

	@Override
	public String batchadd(Dept dto) {
		List<Expression> params = new ArrayList<Expression>();
		params.add(new Expression("id","=",dto.getId()));
		Query<Dept> query = QueryUtils.getQuery(Dept.class, ebeanServer, "find dept", params);
		int count = query.findRowCount();
		String msg = "";
		if(count > 0){
			ebeanServer.update(dto);
		}else{
			ebeanServer.insert(dto);
		}
		return msg;
	}

	@Override
	public void edit(Dept dto) {
		ebeanServer.update(dto);
	}

	@Override
	public void remove(String[] ids){
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(Dept.class, tempId);
            }
        }
	}


}
