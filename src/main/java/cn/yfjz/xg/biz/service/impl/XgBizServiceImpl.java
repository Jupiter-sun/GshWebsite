package cn.yfjz.xg.biz.service.impl;

import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Identities;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.xg.biz.service.XgBizService;
import cn.yfjz.xg.domain.XgBiz;
import cn.yfjz.xg.domain.XgBizField;
import cn.yfjz.xg.domain.XgBizUser;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlQuery;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/9/22.
 */
@Service
public class XgBizServiceImpl implements XgBizService {
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public List<?> toGetColumns(String bizid) {
    	return getColumns(bizid, null);
    }

    private List<?> getColumns(String bizid, String fundid){
    	ShiroUser user = UserAuthUtils.getCurrentUser();
    	String condition = "";
        if(StringUtils.isNotEmpty(fundid)){
            condition = " and f.fundid is null or f.fundid = '" + fundid  + "'";
        }
        String sql = "select f.id,f.biz_id as bizid,f.fieldid,f.fielddesc,f.orderno,f.isdisplay,f.isexport,f.displaywidth,f.url,f.fundid from xg_biz_field f "
        			+" right join xg_biz_user u on f.id = u.biz_field_id where f.biz_id = '" + bizid+"'";
        sql += " and u.user_id = " +user.getId();
        sql += condition ;
        sql += " order by u.orderno asc";
        SqlQuery sqlQuery = ebeanServer.createSqlQuery(sql);
        List<?> userColumns = sqlQuery.findList();
        if(userColumns.size()==0){
        	sql = "select f.id,f.biz_id as bizid,f.fieldid,f.fielddesc,f.orderno,f.isdisplay,f.isexport,f.displaywidth,f.url,f.fundid from xg_biz_field f "
        			+" where f.biz_id = '" + bizid+"' and f.isdisplay = '1' ";
        	sql += condition ;
        	sql += " order by orderno asc";
        	sqlQuery = ebeanServer.createSqlQuery(sql);
        	List<?> commonColumns = sqlQuery.findList();
            return commonColumns;
        }else{
            return userColumns;
        }
    }
    
    public Pager queryPage(Map<String,Object> paramMap,int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = new ArrayList<Expression>();
        for (String key : paramMap.keySet()) {
        	params.add(new Expression(key," = ",paramMap.get(key)));
        }
        Query<XgBiz> query = QueryUtils.getQuery(XgBiz.class, ebeanServer, "find xgBiz", params);
        int count = query.findRowCount();
        OrderBy<XgBiz> orderBy = new OrderBy<XgBiz>("");
        List<XgBiz> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}
    
    public Pager queryXgBizFieldPage(Map<String,Object> paramMap,int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = new ArrayList<Expression>();
        for (String key : paramMap.keySet()) {
        	params.add(new Expression(key," = ",paramMap.get(key)));
        }
        Query<XgBizField> query = QueryUtils.getQuery(XgBizField.class, ebeanServer, "find xgBizField", params);
        int count = query.findRowCount();
        OrderBy<XgBizField> orderBy = new OrderBy<XgBizField>("orderno");
        List<XgBizField> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public void addXgBizField(XgBizField xgBizField) {
		xgBizField.setId(Identities.uuid());
		ebeanServer.insert(xgBizField);
	}

	@Override
	public XgBizField findXgBizFieldById(String id) {
		return ebeanServer.find(XgBizField.class,id);
	}

	@Override
	public void updateXgBizField(XgBizField xgBizField) {
		ebeanServer.update(xgBizField);
	}

	@Override
	public void removeXgBizField(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                ebeanServer.delete(XgBizField.class, id);
            }
        }
	}

	@Override
	public void updateXgBiz(XgBiz xgBiz) {
		ebeanServer.update(xgBiz);
	}

	@Override
	public XgBiz findXgBizById(String id) {
		return ebeanServer.find(XgBiz.class,id);
	}

	@Override
	public List<?> findUnselect(String bizId) {
		ShiroUser currentUser = UserAuthUtils.getCurrentUser();
		String sql = "select t1.* from xg_biz_field t1 where t1.biz_id=:bizId and not exists(select 1 from xg_biz_user t2 where t2.biz_field_id=t1.id and t2.user_id=:userId)";
		SqlQuery sqlQuery = ebeanServer.createSqlQuery(sql).setParameter("bizId", bizId).setParameter("userId", currentUser.getId());
		List<?> list = sqlQuery.findList();
		return list;
	}

	@Override
	public List<?> findSelect(String bizId) {
		ShiroUser currentUser = UserAuthUtils.getCurrentUser();
		String sql = "select * from xg_biz_user t1 left join xg_biz_field t2 on t1.biz_field_id=t2.id where t1.user_id=:userId and  t2.biz_id=:bizId order by t1.orderno";
		SqlQuery sqlQuery = ebeanServer.createSqlQuery(sql).setParameter("userId", currentUser.getId()).setParameter("bizId", bizId);
		List<?> list = sqlQuery.findList();
		return list;
	}

	@Override
	public List findXgBizFieldByBizId(String bizid) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("select t.* from xg_biz_field t where t.biz_id ='"+bizid+"'");
		List list = ebeanServer.createSqlQuery(sql.toString()).findList();
		return list ;
	}

	@Override
	public void save(List xgBizFields, Long userId, String bizId, String[] selected) {
		String deleteSQL = "delete from xg_biz_user t1 where t1.user_id=:userId and exists(select 1 from xg_biz_user t2 left join xg_biz_field t3 on t2.biz_field_id=t3.id where t2.id=t1.id and t3.biz_id=:bizId)";
		ebeanServer.createSqlUpdate(deleteSQL).setParameter("userId", userId).setParameter("bizId", bizId).execute();
        for(int i=0;i<selected.length;i++){
            XgBizUser xgBizUser = new XgBizUser();
            User user = new User();
            user.setId(userId);
            xgBizUser.setUser(user);
            for(int j=0;j<xgBizFields.size();j++){
            	Map map = (Map) xgBizFields.get(j);
                if(selected[i].equals(map.get("fieldid"))){
                	XgBizField xgBizField = new XgBizField();
                	xgBizField.setId(map.get("id").toString());
                	xgBizUser.setBizField(xgBizField);
                    xgBizUser.setOrderno(Long.parseLong((new BigDecimal(i)).toString()));
                    ebeanServer.insert(xgBizUser);
                }
            }
        }
	}
}
