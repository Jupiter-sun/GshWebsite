package cn.yfjz.xg.utils;

import cn.yfjz.core.util.Pager;
import com.avaje.ebean.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengs on 2016-11-30.
 */
public class EbeanUtil<T> {
    public T findById(EbeanServer ebeanServer,Class<T> clazz, String yearId) {
        return ebeanServer.find(clazz,yearId);
    }

    public static List<Map> queryMapList(EbeanServer ebeanServer, String sql) {
        List<Map> result = new ArrayList<Map>();
        List<SqlRow> rows = ebeanServer.createSqlQuery(sql).findList();
        for(SqlRow row : rows){
            result.add(row);
        }
        return result;
    }



    private T sqlRowToModel(SqlRow row,T model,boolean fetch,EbeanServer ebeanServer){
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess( model);
        Field[] superFields = model.getClass().getSuperclass().getDeclaredFields();
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field field : superFields){
            Object fetchObject = null;
            String c = field.getName();
            Column column = field.getAnnotation(Column.class);
            if(column!=null&&StringUtils.isNotEmpty(column.name())){
                c = column.name();
            }
            if(fetch&&field.getAnnotation(ManyToOne.class)!=null&&field.getType().getSuperclass().getName().endsWith(".BaseModel")){
               fetchObject = ebeanServer.find(field.getType(),row.get(c));
            }
            try{

                if(row.containsKey(c)){
                    Method m = getMethod(model,"set" + upperFirstChar(field.getName()));
                    if(m!=null) {
                        if(fetch&&field.getAnnotation(ManyToOne.class)!=null&&field.getType().getSuperclass().getName().endsWith(".BaseModel")){
                            if(fetchObject!=null){
                                //m.invoke(model,fetchObject);
                                beanWrapper.setPropertyValue(field.getName(),fetchObject);
                            }else{
                                continue;
                            }
                        }else {
                            Object o = row.get(c);
                            if(o==null){
                                continue;
                            }
                            beanWrapper.setPropertyValue(field.getName(),o);
                            /*if(o instanceof java.sql.Clob){
                                m.invoke(model, o.toString());
                            }else{
                                m.invoke(model, o.toString());
                            }*/
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        for(Field field : fields){
            try{
                String c = field.getName();
                Column column = field.getAnnotation(Column.class);
                if(column!=null&&StringUtils.isNotEmpty(column.name())){
                    c = column.name();
                }
                Object fetchObject = null;
                if(fetch&&field.getAnnotation(ManyToOne.class)!=null&&field.getType().getSuperclass().getName().endsWith(".BaseModel")){
                    fetchObject = ebeanServer.find(field.getType(),row.get(c));
                }

                if(row.containsKey(c)){
                    Method m = getMethod(model,"set" + upperFirstChar(field.getName()));
                    if(m!=null) {
                        if(fetch&&field.getAnnotation(ManyToOne.class)!=null&&field.getType().getSuperclass().getName().endsWith(".BaseModel")){
                            if(fetchObject!=null){
                                beanWrapper.setPropertyValue(field.getName(),fetchObject);
                            }else{
                                continue;
                            }
                        }else {
                            Object o = row.get(c);
                            if(o==null){
                                continue;
                            }
                            beanWrapper.setPropertyValue(field.getName(),o);
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        return model;
    }

    public List<T> queryForList(EbeanServer ebeanServer, Class<T> clazz, T domain, String entitySql,String orderby,Boolean fetch) {
        Table t = domain.getClass().getAnnotation(Table.class);
        String tableName = t.name();
        StringBuffer sql = new StringBuffer("select * from "+tableName+" ");
        StringBuffer whereSql = new StringBuffer("where 1=1");
        StringBuffer fetchSql = new StringBuffer(" ");
        StringBuffer orderbySql = new StringBuffer("order by "+orderby);
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : superFields){
            try{
                setSqlExpression(domain,field,fetchSql,whereSql);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        for(Field field : fields){
            try{
                setSqlExpression(domain,field,fetchSql,whereSql);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        if(StringUtils.isNotEmpty(entitySql)){
            whereSql.append(entitySql.trim().startsWith("and")?entitySql:" and "+entitySql);
        }
        List<SqlRow> sqlRows = ebeanServer.createSqlQuery(sql.append(" ").append(fetchSql.toString()).append(" ").append(whereSql.toString()).append(" ").append(orderbySql.toString()).toString()).findList();
        List<T> resultList = new ArrayList<T>();
        for(SqlRow row : sqlRows){
            try {
                T t1 = sqlRowToModel(row, clazz.newInstance(),fetch,ebeanServer);
                resultList.add(t1);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public T findFirst(EbeanServer ebeanServer, Class<T> clazz, String sql,Object ... params) {
        List<T> list = this.queryForList(ebeanServer,clazz,sql,params);
        if(list!=null){
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<T> queryForList(EbeanServer ebeanServer, Class<T> clazz, String sql,Object ... params) {
        SqlQuery query = ebeanServer.createSqlQuery(sql);
        if(params!=null&&params.length>0){
            for(int i=0;i<params.length;i++){
                Object param = params[i];
                if(param!=null){
                    if(param instanceof String && StringUtils.isEmpty((String)param)){
                        continue;
                    }else if(param instanceof String && StringUtils.isNotEmpty((String)param)){
                        query.setParameter(i+1,param.toString());
                    }else{
                        query.setParameter(i+1,param);
                    }
                }else{
                    continue;
                }
            }
        }
        List<SqlRow> sqlRows = query.findList();
        List<T> resultList = new ArrayList<T>();
        for(SqlRow row : sqlRows){
            try {
                T t1 = sqlRowToModel(row, clazz.newInstance(),false,ebeanServer);
                resultList.add(t1);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public List<T> queryForList(EbeanServer ebeanServer, Class<T> clazz, T domain,String order) {
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        Query q = ebeanServer.find(domain.getClass());
        ExpressionList eList = q.where();
        for(Field field : superFields){
            try{
                setExpression(domain,field,q,eList);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }finally {
                continue;
            }
        }
        for(Field field : fields){
            try{
                setExpression(domain,field,q,eList);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }finally {
                continue;
            }
        }
        if(StringUtils.isNotEmpty(order)){
            return eList.order(order).findList();

        }else{
            return eList.findList();
        }
    }

    public int queryCount(EbeanServer ebeanServer, Class<T> clazz, T domain, String entitySql) {
        Table t = domain.getClass().getAnnotation(Table.class);
        String tableName = t.name();
        StringBuffer sql = new StringBuffer("select count(*) as zs from "+tableName+" ");
        StringBuffer whereSql = new StringBuffer("where 1=1");
        StringBuffer fetchSql = new StringBuffer(" ");
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : superFields){
            try{
                setSqlExpression(domain,field,fetchSql,whereSql);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        for(Field field : fields){
            try{
                setSqlExpression(domain,field,fetchSql,whereSql);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
        if(StringUtils.isNotEmpty(entitySql)){
            whereSql.append(entitySql.trim().startsWith("and")?entitySql:" and "+entitySql);
        }
        SqlRow sqlRow = ebeanServer.createSqlQuery(sql.append(" ").append(fetchSql.toString()).append(" ").append(whereSql.toString()).toString()).findUnique();
        return sqlRow.getBigDecimal("zs").intValue();
    }

    public static void updateForSql(EbeanServer ebeanServer, String sql, Object ... o) {
        SqlUpdate sqlUpdate = ebeanServer.createSqlUpdate(sql);
        if(o.length>0){
            for(int i=o.length;i<o.length;i++){
                sqlUpdate.setParameter(i,o);
            }
        }
        sqlUpdate.execute();
    }

    public Pager pagination(EbeanServer ebeanServer, T domain, int pageNumber, int pageSize, String order) {
        Pager page = new Pager(pageNumber,pageSize);
        Class clazz = domain.getClass();
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        Query q = ebeanServer.find(domain.getClass());
        ExpressionList eList = q.where();
        for(Field field : superFields){
            try{
                setExpression(domain,field,q,eList);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }finally {
                continue;
            }
        }
        for(Field field : fields){
            try{
                setExpression(domain,field,q,eList);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }finally {
                continue;
            }
        }
        page.setRecords(q.findRowCount());
        page.setRows(q.setFirstRow(page.getStart()).setMaxRows(pageSize).order(order).findList());
        return page;
    }

    private Map<String,Method> getMethods(Object domain){
        Map<String,Method> methodMap= new HashMap<String, Method>();
        Method[] methods = domain.getClass().getDeclaredMethods();
        for(Method m:methods){
            if(m.getName().startsWith("get")||m.getName().startsWith("set")) {
                methodMap.put(m.getName(), m);
            }
        }
        Method[] superMethods = domain.getClass().getSuperclass().getDeclaredMethods();
        for(Method m:superMethods){
            if(m.getName().startsWith("get")||m.getName().startsWith("set")) {
                methodMap.put(m.getName(), m);
            }
        }
        return methodMap;
    }

    private Method getMethod(Object domain,String methodName){
        return getMethods(domain).get(methodName);
    }

    private Map<String,Object> setSqlExpression(T domain,Field field,StringBuffer fetchSql,StringBuffer whereSql) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Table t = domain.getClass().getAnnotation(Table.class);
        Column c = field.getAnnotation(Column.class);
        ManyToOne m = field.getAnnotation(ManyToOne.class);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,Object> query = new HashMap<String,Object>();
        if(m!=null&&c!=null&&StringUtils.isNotEmpty(c.name())&&field.getType().getSuperclass().getName().endsWith(".BaseModel")) {
            getMethod(domain,"");
            Object obj = getMethod(domain,"get" + upperFirstChar(field.getName())).invoke(domain,null);
            if (obj != null) {
                Table t1 = obj.getClass().getAnnotation(Table.class);
                fetchSql.append("left join "+t1.name()+" on "+t.name()+".id = "+c.name());
                Field[] mfield = m.getClass().getDeclaredFields();
                for (Field f : mfield) {
                    Column c1 = field.getAnnotation(Column.class);
                    Object tempPara = getSqlExpressionFieldValue(m,f);
                    if(tempPara!=null){
                        whereSql.append(" and "+t1.name()+"."+c1.name()+" = '"+tempPara+"'");
                    }
                }
            }
        }else if(field.getType().getName().equals("List")||field.getType().getName().equals("Map")){

        }else{
            Object tempPara = getSqlExpressionFieldValue(domain,field);
            if(tempPara!=null){
                if(StringUtils.isNotEmpty(c.name())) {
                    whereSql.append(" and " + t.name() + "." + c.name() + " = '" + tempPara + "'");
                }else{
                    whereSql.append(" and " + t.name() + "." + field.getName() + " = '" + tempPara + "'");
                }
            }
        }
        returnMap.put("query",query);
        return returnMap;
    }

    private Object getSqlExpressionFieldValue(Object domain,Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object param = getMethod(domain,"get" + upperFirstChar(field.getName())).invoke(domain,null);
        if(param!=null){
            if(param instanceof String){
                if(StringUtils.isNotEmpty((String)param)) {
                    return param;
                }else{
                    return null;
                }
            }else{
                return param;
            }
        }else{
            return null;
        }
    }

    private void setExpression(Object domain,Field field,Query q,ExpressionList eList) throws NoSuchMethodException {
        if(field.getType().getSuperclass().getName().endsWith(".BaseModel")) {
            Object m = domain.getClass().getMethod("get" + upperFirstChar(field.getName()), field.getType().getSuperclass());
            if (m != null) {
                q.fetch(field.getName());
                Field[] mfield = m.getClass().getDeclaredFields();
                for (Field f : mfield) {
                    setExpressionFieldValue(eList, m, f);
                }
            }
        }else if(field.getType().getName().equals("List")||field.getType().getName().equals("Map")){
            return;
        }else{
            setExpressionFieldValue(eList,domain,field);
        }
    }

    private void setExpressionFieldValue(ExpressionList eList,Object domain,Field field) throws NoSuchMethodException {
        Object param = domain.getClass().getMethod("get"+upperFirstChar(field.getName()),Object.class);
        if(param!=null){
            if(param instanceof String){
                if(StringUtils.isNotEmpty((String)param)) {
                    eList.eq(field.getName(), param);
                }
            }else{
                eList.eq(field.getName(),param);
            }
        }
    }

    private String upperFirstChar(String target){
        return target.substring(0,1).toUpperCase()+target.substring(1);
    }
}
