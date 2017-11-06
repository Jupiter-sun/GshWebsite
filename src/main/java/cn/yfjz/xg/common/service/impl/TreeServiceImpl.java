package cn.yfjz.xg.common.service.impl;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.sys.service.ClassListService;
import cn.yfjz.core.sys.service.DeptService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.xg.common.service.TreeService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeServiceImpl implements TreeService {
	@Autowired
	private EbeanServer ebeanServer;
    @Autowired
    private  DeptService deptService;
    @Autowired
    private  ClassListService classListService;
    
    public  List sysManagerTree(String id,String pId,String childType){
        List resultList=new ArrayList();
        if (StringUtils.isEmpty(id)) {
            //根节点
            resultList.add(changeRootToMap());
        }else if("xy".equals(childType)){
            //查找学院
            Dept dept = new Dept();
            dept.setIsJxdw("1");
            List<Dept> children = deptService.query(dept);
            if(children!=null && children.size()>0){
                for(Dept child : children){
                    resultList.add(changeXyToMap(child));
                }
            }
        }else if("nj".equals(childType)){
            //查找年级
            List children = this.getNj(id, "");
            if(children!=null && children.size()>0){
                for(Object child : children){
                    resultList.add(changeNjToMap(child));
                }
            }
        }else if("bj".equals(childType)){
            //查找班级
        	ClassList dto = new ClassList();
        	Dept dept = new Dept();
        	dept.setId(Long.parseLong(pId));
            dto.setXy(dept);
            dto.setNj(id);
            List<ClassList> children = classListService.query(dto);
            if(children!=null && children.size()>0){
                for(ClassList child : children){
                    resultList.add(changeBjToMap(child));
                }
            }
        }
        return resultList;
    }

    public List getNj(String xyId,String whereSql){
        String sql= "select distinct nj as id,xy_id as pid,nj as text from P_CLASS where xy_id='"+xyId+"' " ;
        if(StringUtils.isNotEmpty(whereSql)){
            sql+=" and "+whereSql;
        }
        sql+= " order by nj";
        SqlQuery sqlQuery = ebeanServer.createSqlQuery(sql);
		List<?> list = sqlQuery.findList();
        return list;
    }

    private  Map changeRootToMap(){
        Map rootMap = new HashMap();
        rootMap.put("id", CodeConstant.KIND_ROOT_DEFAULT_ID);
        rootMap.put("name", CodeConstant.KIND_ROOT_DEFAULT_NAME);
        rootMap.put("isParent", true);
        rootMap.put("childType","xy");
        return rootMap;
    }
    private  Map changeXyToMap(Dept dept){
        Map xyMap=new HashMap();
        xyMap.put("id", dept.getId());
        xyMap.put("name", dept.getName());
        xyMap.put("pId", CodeConstant.KIND_ROOT_DEFAULT_ID);
        xyMap.put("isParent", true);
        xyMap.put("childType","nj");
        return xyMap;
    }
    private  Map changeNjToMap(Object model){
    	Map map = (Map) model;
        Map njMap=new HashMap();
        njMap.put("id",map.get("id"));
        njMap.put("name", map.get("text"));
        njMap.put("pId",map.get("pid"));
        njMap.put("isParent", true);
        njMap.put("childType","bj");
        return njMap;
    }
    private  Map changeBjToMap(ClassList bj){
        Map bjMap=new HashMap();
        bjMap.put("id", bj.getId());
        bjMap.put("name", bj.getBjmc());
        bjMap.put("pId", bj.getNj());
        bjMap.put("isParent", false);
        return bjMap;
    }
}
