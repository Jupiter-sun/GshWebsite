package cn.yfjz.xg.common.service;

import cn.yfjz.xg.domain.Demo;

import java.util.List;

/**
 * Created by 杨茗心 on 2016/10/28.
 */
public interface Demoservice {
    void add(Demo demo);

    void edit(Demo demo);

    List<Demo> getAllByExecl(String file);

    List<Demo> getAllDemo();

    boolean isExist(String xm);

    Demo findByxm(String xm);
}
