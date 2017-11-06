package cn.yfjz.xg.info.service;

import java.util.List;
import java.util.Map;

import cn.yfjz.core.util.Pager;
import cn.yfjz.xg.domain.Channel;

/**
 * Created by yuanye
 * @Date：2016年8月19日 
 * @Time：上午10:36:57 
 */
public interface ChannelService {
    Channel findById(Long id);
    List<Map<String, Object>> tree(String pid) ;
    Pager queryPage(Map<String,Object> paramMap,Long pId,int pageNum,int pageSize);
    void add(Channel dto);
    void edit(Channel dto);
    void remove(String[] ids);
    List getChannels();
}
