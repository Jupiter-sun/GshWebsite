package cn.yfjz.core.util;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.SqlParam;
import com.avaje.ebean.*;
import org.apache.commons.lang3.StringUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 2016/5/23.
 */
public class QueryUtils {
    public static <T> Query<T> getQuery(Class<T> bean,EbeanServer server, String oql, List<Expression> params) {
        oql +=" where 1=1 ";
        if(params!=null && params.size()>0){
            for(int i=0; i<params.size(); i++){
                oql += " and "+params.get(i).getKey()+ params.get(i).getExpr()+"?";
            }
        }
        Query<T> query = server.createQuery(bean, oql);
        if(params!=null && params.size()>0){
            for(int i=0; i<params.size(); i++){
                query.setParameter(i+1, params.get(i).getValue());
            }
        }
        return query;
    }
    public static <T> Query<T> getQuery_Or(Class<T> bean,EbeanServer server, String oql, List<Expression> params,String statu) {
        if (StringUtils.isNotEmpty(statu)){
            oql +=" where 1=1 and status='"+statu+"'";
        }else {
            oql +=" where 1=1 ";
        }
        if(params!=null && params.size()>0){
            oql += "and (";
            for(int i=0; i<params.size(); i++){

                if(i==0){
                    oql += params.get(i).getKey()+ params.get(i).getExpr()+"?";
                }else {
                    oql += " or "+params.get(i).getKey()+ params.get(i).getExpr()+"?";
                }
            }
            oql += ")";
        }
        Query<T> query = server.createQuery(bean, oql);
        if(params!=null && params.size()>0){
            for(int i=0; i<params.size(); i++){
                query.setParameter(i+1, params.get(i).getValue());
            }
        }
        return query;
    }

    public static <T> Query<T> createQuery(Class<T> bean,EbeanServer server, String oql, Map<String, String> paramMap) {
        List<Expression> params = new ArrayList<Expression>();
        if(paramMap!=null && paramMap.size()>0){
            for (String key : paramMap.keySet()) {
                params.add(new Expression(key," = ",paramMap.get(key)));
            }
        }
        return getQuery(bean, server, oql, params);
    }

    public static <T extends BaseModel> ExpressionList<T> getExpressionList(Class<T> bean,EbeanServer server, T baseModel) {
        Query<T> query = server.find(bean);
        ExpressionList expressionList = query.where();
        baseModel.setExpressions(expressionList);
        return expressionList;
    }

    public static Pager getPager(EbeanServer server, String listSql, String countSql, int pageNum, int pageSize){
        Pager page=new Pager(pageNum,pageSize);
        listSql = getLimitString(listSql, page.getStart(), pageSize);
        SqlQuery listQuery = server.createSqlQuery(listSql);
        SqlQuery countQuery = server.createSqlQuery(countSql);
        List list = listQuery.findList();
        int count = countQuery.findUnique().getInteger("count");
        page.setRecords(count);
        page.setRows(list);
        return page;
    }


    private static String getLimitString(String sql, int offset, int limit) {
        if(0 == offset && Integer.MAX_VALUE == limit){
            return sql;
        }
        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        pagingSelect.append(sql);
        pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);
        return pagingSelect.toString();
    }


    public static String executeProcedure(EbeanServer server, String sql, List<SqlParam> paraType){
        String procedure = "{call "+sql+"(";
        if(paraType!=null && paraType.size()>0){
            for(int i=0; i<paraType.size(); i++){
                procedure+="?";
                if(i<paraType.size()-1){
                    procedure+=",";
                }
            }
        }
        procedure+=")}";
        CallableSql callableSql = server.createCallableSql(procedure);
        boolean callback = false;
        int callindex = 0;
        if(paraType!=null && paraType.size()>0){
            for(int i=0; i<paraType.size(); i++){
                SqlParam sqlParam = paraType.get(i);
                if(sqlParam.getTypeName().toUpperCase().equals("OUT")){
                    callback = true;
                    callindex = i+1;
                    callableSql.registerOut(i+1, Types.VARCHAR);
                }
                else{
                    callableSql.setParameter(i+1, sqlParam.getValue());
                }
            }
        }
        server.execute(callableSql);
        if(callback){
            return (String) callableSql.getObject(callindex);
        }
        return null;
    }

}
