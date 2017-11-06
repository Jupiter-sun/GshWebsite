package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Job;
import cn.yfjz.core.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/9/30.
 */
public interface JobService {
    Pager queryPage(int pageNum, int pageSize);
    Job findById(String id);
    void add(Job job);
    void edit(Job job);
    void remove(String[] ids);
    List query(Map<String, Object> paramMap);
}
