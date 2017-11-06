package cn.yfjz.core.sys.service;

import cn.yfjz.core.sys.domain.Config;
import cn.yfjz.core.util.Pager;

/**
 * Created by liwj on 16/8/9.
 */
public interface ConfigService {
    public String findValueByKey(String key, String defaultValue);
    public Pager queryPage(int pageNum, int pageSize);

    Config findById(String id);
    Config findByKey(String key);
    void edit(Config config);
}
