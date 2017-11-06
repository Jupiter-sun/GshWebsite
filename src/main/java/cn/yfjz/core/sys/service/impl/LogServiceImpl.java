package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.Log;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.core.sys.service.LogService;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwj on 2016/5/18.
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public void save(Log log) {
        ebeanServer.save(log);
    }

    @Override
    public Pager queryPage(String ksrq, String jsrq,int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        List<Expression> params = new ArrayList<Expression>();
        if(StringUtils.isNotEmpty(ksrq)){
            params.add(new Expression("whenCreated",">=",Date.valueOf(ksrq)));
        }
        if(StringUtils.isNotEmpty(jsrq)){
            params.add(new Expression("whenCreated","<=",Date.valueOf(jsrq)));
        }
        Query<Log> query = QueryUtils.getQuery(Log.class, ebeanServer, "find log", params);
        int count = query.findRowCount();
        List<Log> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).orderBy(" whenCreated desc").findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }
}
