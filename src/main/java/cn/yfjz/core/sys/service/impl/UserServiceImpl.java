package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.security.utils.RoleUtil;
import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.core.sys.domain.*;
import cn.yfjz.core.sys.service.DeptService;
import cn.yfjz.core.sys.service.RoleService;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.core.util.*;


import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlUpdate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 2016/5/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EbeanServer ebeanServer;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;
    @Override
    public Pager queryPage(User user,int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        List<Expression> params = user.getCondition(user);
        Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
        int count = query.findRowCount();
        List<User> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }
    //找到存在的数据
    @Override
    public Pager queryPageByperson(User user,int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        List<Expression> params = user.getCondition(user);
        Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
        String where  =" and 1=1 ";
        where += " and exists (select * from d_person p where p.manager_id=id)";
        query = query.where(where);
        int count = query.findRowCount();
        List<User> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }
    @Override
    public void add(User user) {
        ebeanServer.insert(user);
    }
    @Override
    public void batchadd(User user) {
        List<Expression> params = new ArrayList<Expression>();
        params.add(new Expression("username","=",user.getUsername()));
        params.add(new Expression("truename","=",user.getTruename()));
        Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
        int count = query.findRowCount();
        String msg = "";
        if(count > 0){
            User oldUser=findByUserId(user.getUserid());
            user.setId(oldUser.getId());
             ebeanServer.update(user);
        }else{
            ebeanServer.insert(user);
        }

    }
    @Override
    public void edit(User user) {
        ebeanServer.update(user);
    }

    @Override
    public void remove(Long id) {
        ebeanServer.delete(User.class, id);
    }

    @Override
    public User findById(Long id) {
        return ebeanServer.find(User.class, id);
    }

    @Override
    public User findByUserId(String userid){
        return ebeanServer.find(User.class).where().eq("userid",userid).findUnique();
    }

    @Override
    public List<User> findByDept(Long id) {
        return ebeanServer.find(User.class).where().eq("dept_id",id).findList();
    }

    @Override
    public List queryChildDepts(long fid) {
        return ebeanServer.createSqlQuery("select * from ( " +
                " select connect_by_isleaf isleaf, p_dept.* from p_dept start with father_id is null connect by father_id = prior id ）" +
                "where father_id = "+fid ).findList();
    }

    public Dept findDept(long id){
        return ebeanServer.find(Dept.class,id);
    }

    @Override
    public List getDepts() {
        return ebeanServer.find(Dept.class).findList();
    }

    @Override
    public List findAll() {
        return ebeanServer.find(User.class).findList();
    }

    @Override
    public Map<String, Object> updatePassword(String oldPass, String confirmPass) {
        Map<String,Object> resultMap=new HashMap<String, Object>();
        Long id = UserAuthUtils.getCurrentUser().getId();
        User user = ebeanServer.find(User.class, id);
        resultMap.put("status", false);
        if(user==null){
            resultMap.put("msg", "该用户不存在!");
            return resultMap;
        }
        String crptPwd= CryptUtil.cryptPwd(oldPass, user.getSalt());
        if(!crptPwd.equals(user.getPassword())){
            resultMap.put("msg", "原密码错误!");
            return resultMap;
        }
        if(confirmPass.equals(oldPass)){
            resultMap.put("msg", "原密码和新密码不能相同！");
            return resultMap;
        }
        byte[] salt = Digests.generateSalt(8);
        user.setSalt(Encodes.encodeHex(salt));
        String newCrptPwd=CryptUtil.cryptPwd(confirmPass, user.getSalt());
        user.setPassword(newCrptPwd);
        edit(user);
        resultMap.put("status", true);
        return resultMap;
    }

    @Override
	public Pager getPagerByRole(Long roleid,Map<String, Object> paramMap, int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        List<Expression> params = new ArrayList<Expression>();
        for (String key : paramMap.keySet()) {
        	if(paramMap.get(key) !=null){
                if("xm".equals(key) || "username".equals(key) ){
                        paramMap.put(key, "%"+paramMap.get(key).toString()+"%");
                        params.add(new Expression(key," like ",paramMap.get(key)));
                    }else{
                        params.add(new Expression(key," = ",paramMap.get(key)));
                    }
                }
        }
        Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
        Role role = ebeanServer.find(Role.class,roleid);
        int count = query.where().in("roles", role).findRowCount();
        List<User> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize)
        		.where().in("roles", role)
        		.findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public void grant(Long userid, String[] roleids) {
		String delSql = "delete from p_user_role where user_id = "+userid;
        SqlUpdate sqlDel  = ebeanServer.createSqlUpdate(delSql);
		ebeanServer.execute(sqlDel);
		if(roleids!=null&&roleids.length>0){
            for(String roleid: roleids){
            	String insertSql = "insert into p_user_role(user_id,role_id) values("+userid+","+"'"+Long.parseLong(roleid)+"')";
                SqlUpdate insertUpdate  = ebeanServer.createSqlUpdate(insertSql);
        		ebeanServer.execute(insertUpdate);
            }
        }
	}

	@Override
    public boolean isUserExist(String username) {
        boolean flag = true;
        String sql = " select count(1) as count from p_user where username = '"+username+"' ";
        SqlQuery countQuery = ebeanServer.createSqlQuery(sql);
        List countList = countQuery.findList();
        int count = countList.size();
        if(count==0)flag = false;
        return  flag;
    }

	@Override
	public void addStudentUser(Map student) {
		User user = new User();
        user.setUsername(student.get("xh").toString());
        user.setTruename(student.get("xm").toString());
        byte[] salt = Digests.generateSalt(8);
        user.setSalt(Encodes.encodeHex(salt));
        String newCrptPwd=CryptUtil.cryptPwd(student.get("xh").toString(), user.getSalt());
        user.setPassword(newCrptPwd);
        user.setType(CodeConstant.USER_TYPE_STUDENT);
        if(!this.isUserExist(student.get("xh").toString())) {
            ebeanServer.save(user);
            this.addRole(student.get("xh").toString(), RoleUtil.SYS_STUDENT);
        }
	}
	
	@Override
	public void addRole(String username,String roleCode){
        User user = getUserByUsername(username);
        Role role = roleService.findRoleByCode(roleCode);
        if( null != user) {
            if (StringUtils.isNotEmpty(role.getId().toString())) {
                String sql = "select count(1) as count from p_user_role where user_id='" + user.getId() + "' and role_id='" + role.getId() + "'";
                int count = ebeanServer.createSqlQuery(sql).findUnique().getInteger("count");
                if (count <= 0) {
                    String insertSql = "insert into p_user_role(user_id,role_id) values ('"+user.getId()+"','"+role.getId()+ "')";
                    SqlUpdate insertUpdate  = ebeanServer.createSqlUpdate(insertSql);
            		ebeanServer.execute(insertUpdate);
                }
            }
        }
    }




    public User getUserByUsername(String username){
        List list = ebeanServer.find(User.class).where().eq("username", username).findList();
        if( null == list || list.size() == 0){
            return null;
        }else{
            return (User)list.get(0);
        }
    }

    @Override
    public User getUserByTrueName(String trueName) {
        return ebeanServer.find(User.class).where().eq("truename",trueName).findUnique();
    }

    @Override
    public Pager selectUserPage(User user, int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        List<Expression> params = user.getCondition(user);
        Query<User> query = QueryUtils.getQuery(User.class, ebeanServer, "find user", params);
        int count = query.findRowCount();
        List<User> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }


}
