package cn.yfjz.xg.info.service;

import java.util.Map;

import cn.yfjz.core.util.Pager;
import cn.yfjz.xg.domain.Content;

/**
 * Created by yuanye
 * @Date：2016年8月23日 
 * @Time：下午3:24:56 
 */
public interface ContentService {
    Pager queryPage(Map<String,Object> paramMap,Long pId,int pageNum,int pageSize);
    void add(Content dto);
    Content findById(Long id);
    void remove(String[] ids);
    void edit(Content dto);
}
