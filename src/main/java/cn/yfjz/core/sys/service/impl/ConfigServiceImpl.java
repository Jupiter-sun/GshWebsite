package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.Config;
import cn.yfjz.core.sys.service.ConfigService;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liwj on 16/8/9.
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public String findValueByKey(String key, String defaultValue) {
        if(StringUtils.isEmpty(key)){
            return defaultValue;
        }
        Config config = ebeanServer.find(Config.class).where().eq("key1", key).findUnique();
        if(config!=null){
            return config.getValue1();
        }
        return defaultValue;
    }

    @Override
    public Pager queryPage(int pageNum, int pageSize) {
        Pager page = new Pager(pageNum, pageSize);
        Query<Config> query = QueryUtils.getQuery(Config.class, ebeanServer, "find config", null);
        int count = query.findRowCount();
        List<Config> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).orderBy(" whenCreated desc").findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
    }

    @Override
    public Config findById(String id) {
        return ebeanServer.find(Config.class, id);
    }

    @Override
    public Config findByKey(String key) {
        return  ebeanServer.find(Config.class).where().eq("key1",key).findUnique();
    }

    @Override
    public void edit(Config config) {
        if(config.getId()==null){
            Config config1 = findByKey(config.getKey1());
            config.setId(config1.getId());
        }
        ebeanServer.update(config);
    }
}
