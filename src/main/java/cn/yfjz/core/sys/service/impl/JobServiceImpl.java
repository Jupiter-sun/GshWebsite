package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.Job;
import cn.yfjz.core.sys.service.JobService;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/9/30.
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private EbeanServer ebeanServer;

    //页面查询
    @Override
    public Pager queryPage(int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        int count = ebeanServer.find(Job.class).findRowCount();
        List<Job> list = ebeanServer.find(Job.class).setFirstRow(page.getStart()).setMaxRows(pageSize).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }

    //根据ID查询
    @Override
    public Job findById(String id) {
        return ebeanServer.find(Job.class, id);
    }

    //添加Job
    @Override
    public void add(Job job) {
        ebeanServer.insert(job);
    }

    //编辑job
    @Override
    public void edit(Job job) {
        ebeanServer.update(job);
    }

    //删除job
    @Override
    public void remove(String[] ids) {
        if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                ebeanServer.delete(Job.class, id);
            }
        }
    }

    //查询所有的job
    @Override
    public List query(Map<String, Object> paramMap) {
        List<Expression> params = new ArrayList<Expression>();
        for (String key : paramMap.keySet()) {
            params.add(new Expression(key," = ",paramMap.get(key)));
        }
        Query<Job> query = QueryUtils.getQuery(Job.class, ebeanServer, "find job", params);
        List<Job> list = query.findList();
        return list;
    }

}
