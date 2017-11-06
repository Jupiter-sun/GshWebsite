package cn.yfjz.xg.common.service.impl;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.sys.domain.XgCode;
import cn.yfjz.xg.common.service.SelectService;
import cn.yfjz.xg.domain.SelectKey;
import cn.yfjz.xg.domain.Xueyuan;
import com.avaje.ebean.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liwj on 16/9/24.
 */
@Service
public class SelectServiceImpl implements SelectService {
    @Autowired
    private EbeanServer ebeanServer;

    @Override
    public List<Xueyuan> queryXueyuan() {
        String sql = "select id as xyId,code as xyh,name as xy,order_number as orderNumber from p_dept where is_jxdw='1' order by order_number";
        RawSql rawSql = RawSqlBuilder.parse(sql).create();
        Query<Xueyuan> query = ebeanServer.find(Xueyuan.class);
        query.setRawSql(rawSql);
        return query.findList();
    }

    @Override
    public List<Major> queryMajor(String xyIds, String njs) {
        String xySql = "";
        if("xy.id".equals(xyIds))
        xyIds=null;
        if(StringUtils.isNotEmpty(xyIds)){
            String[] xyarr = xyIds.split(",");
            xySql += "(";
            for(int i=0; i<xyarr.length; i++){
                if(i>0){
                    xySql += " or ";
                }
                if("xy.id".equals(xyarr[i]))
                    break;
                xySql += " xy_id='"+xyarr[i]+"'";
            }
            xySql += ")";
        }
        String njSql = "";
        if(StringUtils.isNotEmpty(njs)){
            String[] njarr = njs.split(",");
            njSql += "(";
            for(int i=0; i<njarr.length; i++){
                if(i>0){
                    njSql += " or ";
                }
                njSql += " nj='"+njarr[i]+"'";
            }
            njSql += ")";
        }
        String conditionSql = " where 1=1 ";
        if(StringUtils.isNotEmpty(xySql)){
            conditionSql += " and "+xySql;
        }
        if(StringUtils.isNotEmpty(njSql)){
            conditionSql += " and "+njSql;
        }
        Query<Major> query = ebeanServer.createQuery(Major.class, conditionSql);
        return query.findList();
    }

    @Override
    public List<ClassList> queryClasslist(String xyIds, String njs, String zyIds) {
        String xySql = "";
        if("xy.id".equals(xyIds))
        xyIds=null;
        if("zy".equals(zyIds))
        zyIds=null;
        if(StringUtils.isNotEmpty(xyIds)){
            String[] xyarr = xyIds.split(",");
            xySql += "(";
            for(int i=0; i<xyarr.length; i++){
                if(i>0){
                    xySql += " or ";
                }
                xySql += " xy_id='"+xyarr[i]+"'";
            }
            xySql += ")";
        }
        String njSql = "";
        if(StringUtils.isNotEmpty(njs)){
            String[] njarr = njs.split(",");
            njSql += "(";
            for(int i=0; i<njarr.length; i++){
                if(i>0){
                    njSql += " or ";
                }
                njSql += " nj='"+njarr[i]+"'";
            }
            njSql += ")";
        }

        String zySql = "";
        if(StringUtils.isNotEmpty(zyIds)){
            String[] zyarr = zyIds.split(",");
            zySql += "(";
            for(int i=0; i<zyarr.length; i++){
                if(i>0){
                    zySql += " or ";
                }
                zySql += " major_id='"+zyarr[i]+"'";
            }
            zySql += ")";
        }

        String conditionSql = " where 1=1 ";
        if(StringUtils.isNotEmpty(xySql)){
            conditionSql += " and "+xySql;
        }
        if(StringUtils.isNotEmpty(njSql)){
            conditionSql += " and "+njSql;
        }
        if(StringUtils.isNotEmpty(zySql)){
            conditionSql += " and "+zySql;
        }
        Query<ClassList> query = ebeanServer.createQuery(ClassList.class, conditionSql);
        return query.findList();
    }

    @Override
    public List<XgCode> queryCommon(SelectKey key, String parentId) {
        String sql = "";
        switch (key){
            case dept:
                sql = "select id,name as value from p_dept where status='1' order by order_number";
                break;
            case role:
                sql = "select id,name as value from p_role";
                break;
            case roleStd:
                sql = "select id,name as value from p_role where is_std_info = '1'";
                break;
            case zzlb:
                sql = "select id,name as value from xg_poor_type where is_valid='1'";
                break;
            case process:
                sql = "select id, name as value from xg_process_def";
                break;
            case zzfz:
                sql = "select poor_group_id as id,zmc as value from xg_poor_group";
                break;
            case code:
                if (StringUtils.isNotEmpty(parentId)){
                    sql = "select id,item_name as value from p_code_item where parent_id='"+parentId+"' and status='1' order by order_no";
                }
                break;
            case tableType:
                sql = "select typeid as id, typename as value from std_table_type";
                break;
            case zxs:
                sql = "select id,xm as value from xg_consultation_expert t where sfyx='1'";
                break;
            case poorFund:
                sql = "select id,jjmc as value from xg_poor_fund t where t.type_id = '"+parentId+"' order by jjbh";
                break;
            case user:
                sql = " select id,truename as value from p_user where dept_id = '"+parentId+"' ";
                break;
            case manager:
                sql = " select id,truename as value from p_user ";
                break;
            case member:
                sql = " select id,truename as value from p_user ";
                break;
            case reportPerson:
                sql = " select id,truename as value from p_user ";
                break;
            case area:
                sql = " select id,position as value from d_turn_out ";
                break;
            case troubleType:
                sql = " select i.id, i.item_name as value from p_code_kind k inner join p_code_item i on k.id=i.kind_id where k.id=2";
                break;
            case active:
                sql = " select id, active_name as value from d_active";
                break;
            case novalue:
                break;
            default:
                break;
        }
        RawSql rawSql = RawSqlBuilder.parse(sql).create();
        Query<XgCode> query = ebeanServer.find(XgCode.class);
        query.setRawSql(rawSql);
        return query.findList();
    }

    @Override
    @Cacheable(value="baseDataCache",key="'njlist'")
    public List<String> queryNj() {
        List<String> njlist = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for(int i=0; i<10; i++){
            njlist.add((currentYear-i)+"");
        }
        return njlist;
    }

    public List<Map> queryXn() {
        List<Map> xnlist = new ArrayList<Map>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for(int i=0; i<10; i++){
            Map map = new HashMap<String, String>();
            map.put("id", (currentYear-i));
            map.put("value", (currentYear-i) +"-" + (currentYear + 1 - i));
            xnlist.add(map);
        }
        return xnlist;
    }
}
