package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.domain.SqlParam;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.core.sys.service.ImportService;
import cn.yfjz.xg.domain.ImportCheckRule;
import cn.yfjz.xg.domain.ImportColumn;
import cn.yfjz.xg.domain.ImportModel;
import com.avaje.ebean.EbeanServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/11/21.
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public List<ImportColumn> queryColumns(String impId) {
        return ebeanServer.find(ImportColumn.class).where().eq("imp_id", impId).findList();
    }

    @Override
    public ImportModel findById(String id) {
        return ebeanServer.find(ImportModel.class, id);
    }

    @Override
    public List<Map> saveTemp(ImportModel pImport, List<ImportColumn> columns, String pc, List<String> titles, List<List<String>> content, List<String> cList) {
        List<Map> errorList = onlySaveTemp(pImport, columns, pc, titles, content, cList);
        ShiroUser user = UserAuthUtils.getCurrentUser();
        if(errorList!=null && errorList.size()>0){
            ebeanServer.createSqlUpdate("delete from "+pImport.getTableName()+" where user_id="+user.getId()+" and pc='"+pc+"'").execute();
        }
        return errorList;
    }

    private List<Map> onlySaveTemp(ImportModel pImport, List<ImportColumn> columns, String pc, List<String> titles, List<List<String>> content, List<String> cList){
        List<Map> errorList = new ArrayList<Map>();

        List<Integer> t_list = new ArrayList<Integer>();
        String columnSql = "";
        for(int i=0; i<columns.size(); i++){
            String showName = columns.get(i).getShowName();
            if(titles.contains(showName)){
                t_list.add(titles.indexOf(showName));
                columnSql+=" ,"+columns.get(i).getColumnName();
                if( "1".equals(columns.get(i).getIsColumn())) {
                    cList.add(columns.get(i).getColumnName());
                }
            }
        }
        //导入到临时表，并检查数据
        ShiroUser user = UserAuthUtils.getCurrentUser();
        String insertSql = "insert into "+pImport.getTableName()+"(id, user_id, pc, when_created"+columnSql+") values (sys_guid(),"+user.getId()+",'"+pc+"',sysdate";
        for(int i=0; i<content.size(); i++){
            String sql = insertSql;
            List<String> row = content.get(i);
            for(int j=0; j<t_list.size(); j++){
                sql += " ,'"+row.get(t_list.get(j))+"'";
            }
            sql += ")";
            ebeanServer.createSqlUpdate(sql).execute();
        }
        //检查数据
        List<ImportCheckRule> rules = ebeanServer.find(ImportCheckRule.class).where().eq("imp_id", pImport.getId()).findList();
        if(rules!=null && rules.size()>0){
            for(int i=0; i<rules.size(); i++){
                ImportCheckRule rule = rules.get(i);
                String checkSql = "select * from "+pImport.getTableName()+" s where user_id="+user.getId()+" and pc='"+pc+"'";
                checkSql += " and ("+rule.getCheckRule()+")";
                List checkList = ebeanServer.createSqlQuery(checkSql).findList();
                if(checkList!=null && checkList.size()>0){
                    errorList.add(setErrorMap(rule.getRuleName(), checkList));
                }
            }
        }
        return errorList;
    }
    private Map setErrorMap(String msg, List list){
        Map map = new HashMap();
        map.put("errormsg", msg);
        map.put("errorlist", list);
        return map;
    }

    @Override
    public String[] saveData(String importId, String pc, List<String> cList) {
        ImportModel pImport = this.findById(importId);
        ShiroUser user = UserAuthUtils.getCurrentUser();
        List<SqlParam> params = new ArrayList<SqlParam>();
        //OUT参数，返回值
        params.add(new SqlParam());
        //批次参数
        params.add(new SqlParam(Types.VARCHAR, pc));
        //用户id参数
        params.add(new SqlParam(Types.NUMERIC, user.getId()));
        params.add(new SqlParam(Types.VARCHAR, transfer2String(cList)));
        //执行存储过程
        String callBackMsg = QueryUtils.executeProcedure(ebeanServer, pImport.getExportProcedure(), params);
        if(StringUtils.isNotEmpty(callBackMsg)){
            return callBackMsg.split(",");
        }
        return null;
    }

    /*把传过来的表头转换成String*/
    public String transfer2String(List<String> cList){
        StringBuffer buff = new StringBuffer();
        for(int i = 0; i < cList.size(); i++){
            if( buff.length() > 0 ){
                buff.append(" ,");
            }
            buff.append(cList.get(i));
        }
        return buff.toString();
    }
}
