package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Log;
import cn.yfjz.core.util.Pager;

/**
 * Created by liwj on 2016/5/18.
 */
public interface LogService {
    public void save(Log log);
    public Pager queryPage(String ksrq, String jsrq,int pageNum, int pageSize);
}
