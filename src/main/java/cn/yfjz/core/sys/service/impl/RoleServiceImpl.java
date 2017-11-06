package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.sys.domain.RolePerm;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.sys.service.RoleService;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlUpdate;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 2016/5/19.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public Pager queryPage(int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        int count = ebeanServer.find(Role.class).findRowCount();
        List<Role> list = ebeanServer.find(Role.class).setFirstRow(page.getStart()).setMaxRows(pageSize).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }

    @Override
    public Role findById(Long id) {
        return ebeanServer.find(Role.class, id);
    }

    @Override
    public void add(Role role) {
        ebeanServer.insert(role);
    }

    @Override
    public void edit(Role role) {
        ebeanServer.update(role);
    }

    @Override
    public void remove(String[] ids) {
        if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(Role.class, tempId);
            }
        }
    }

    @Override
    public List querySysByIds(String[] ids) {
        String sql = "select * from p_role where code like 'SYS%' and (";
        for(int i=0; i<ids.length; i++){
            if(i>0){
                sql += " or ";
            }
            sql += "id="+ids[i];
        }
        sql += ")";
        List list = ebeanServer.createSqlQuery(sql).findList();
        return list;
    }

    @Override
	public List selectPermModelByRoleId(Long roleid) {
		Role role = ebeanServer.find(Role.class, roleid);
		return ebeanServer.find(RolePerm.class).where().eq("role", role).findList();
	}

	@Override
	public List query(Map<String, Object> paramMap) {
		List<Expression> params = new ArrayList<Expression>();
		for (String key : paramMap.keySet()) {
        	params.add(new Expression(key," = ",paramMap.get(key)));
        }
		Query<Role> query = QueryUtils.getQuery(Role.class, ebeanServer, "find role", params);
        List<Role> list = query.findList();
        return list;
	}

    @Override
    public void grantPerms(String roleId, String[] permStrings) {
        String delSql = "delete from p_role_perm where role_id = "+roleId;
        SqlUpdate sqlDel  = ebeanServer.createSqlUpdate(delSql);
		ebeanServer.execute(sqlDel);
		if(permStrings!=null&&permStrings.length>0){
            for(String permString: permStrings){
            	String insertSql = "insert into p_role_perm(role_id,perm_string) values("+roleId+","+"'"+permString+"')";
                SqlUpdate insertUpdate  = ebeanServer.createSqlUpdate(insertSql);
        		ebeanServer.execute(insertUpdate);
            }
        }
    }

    //插入角色用户[JIE.LI 2016/10/11]
    @Override
    public void addRoleUser(String roleid,String[] usersid){
        if((usersid!=null&&usersid.length>0)&&(roleid!=null)){
            for(String userid: usersid){
                String insertSql = "insert into  p_user_role(user_id,role_id) values("+userid+","+"'"+roleid+"')";
                SqlUpdate insertUpdate  = ebeanServer.createSqlUpdate(insertSql);
                ebeanServer.execute(insertUpdate);
            }
        }
    }

    //删除角色用户[JIE.LI 2016/10/11]
    @Override
    public void removeRoleUser(String roleid,String[] usersid){
        if((usersid!=null&&usersid.length>0)&&(roleid!=null)){
            for(String userid: usersid){
                String sql = "delete from p_user_role where user_id= '"+userid+"' and role_id='"+roleid+"'";
                SqlUpdate delete  = ebeanServer.createSqlUpdate(sql);
                ebeanServer.execute(delete);
            }
        }
    }

    //检查选中用户是否已经在角色用户表中存在[JIE.LI 2016/10/11]
    @Override
   public boolean checkRoleUser(String roleid,String[] usersid){
        int count=0;
        if((usersid!=null&&usersid.length>0)&&(roleid!=null)){
            for(int i=0;i<usersid.length;i++){
                List<Expression> params = new ArrayList<Expression>();
                params.add(new Expression("id"," = ",usersid[i]));
                Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
                Role role = ebeanServer.find(Role.class,roleid);
                List<User> list = query.where().in("roles", role).findList();
                if(!list.isEmpty()){
                    count++;
                }
            }
        }
        if(count!=0){
            return false;
        }else{
            return true;
        }
    }

	@Override
	public Role findRoleByCode(String code) {
	     return ebeanServer.find(Role.class).where().eq("code", code).findUnique();
	}
}
