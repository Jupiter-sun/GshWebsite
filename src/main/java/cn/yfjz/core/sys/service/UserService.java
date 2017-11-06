package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 2016/5/16.
 */
public interface UserService {
    Pager queryPage(User user,int pageNum, int pageSize);
    public Pager queryPageByperson(User user,int pageNum, int pageSize);
    public Pager selectUserPage(User user, int pageNum, int pageSize);
    void add(User user);
    public void batchadd(User user);
    void edit(User user);
    void remove(Long id);
    User findById(Long id);
    public User findByUserId(String userid);
    List queryChildDepts(long fid);
    public List<User> findByDept(Long id);
    Dept findDept(long id);
    List getDepts();
    List findAll();
    Map<String,Object> updatePassword(String oldPass, String confirmPass);
    Pager getPagerByRole(Long roleid,Map<String, Object> paramMap,int pageNum, int pageSize);
    void grant(Long userid,String[] roleids);
    /*新增学生用户 并赋权*/
    public void addStudentUser(Map student);
    //判断用户是否存在
    public  boolean isUserExist(String username);
    public User getUserByUsername(String username);
    public User getUserByTrueName(String username);
    public void addRole(String username,String roleCode);

}
