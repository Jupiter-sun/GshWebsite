package cn.yfjz.xg.biz.service;

import java.util.List;
import java.util.Map;

import cn.yfjz.core.util.Pager;
import cn.yfjz.xg.domain.XgBiz;
import cn.yfjz.xg.domain.XgBizField;

/**
 * Created by liwj on 16/9/22.
 */
public interface XgBizService {
    /**
     * 根据bizid获取列（默认使用XgBizField中列）
     * @param bizid
     * @return
     */
    public List<?> toGetColumns(String bizid);
    Pager queryPage(Map<String,Object> paramMap,int pageNum, int pageSize);
    Pager queryXgBizFieldPage(Map<String,Object> paramMap,int pageNum, int pageSize);
    void addXgBizField(XgBizField xgBizField);
    XgBizField findXgBizFieldById(String id);
    XgBiz findXgBizById(String id);
    void updateXgBizField(XgBizField xgBizField);
    void updateXgBiz(XgBiz xgBiz);
    void removeXgBizField(String[] ids);
    List<?> findUnselect(String bizId);
    List<?> findSelect(String bizId);
    List<?> findXgBizFieldByBizId(String bizid);
    public void save(List<XgBizField> xgBizFields, Long userid, String bizid, String[] selected);
}
