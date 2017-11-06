package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.ProjectServer;
import cn.yfjz.website.service.ProjectService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private EbeanServer ebeanServer;

    @Override
    public List<ProjectServer> queryAll(){
        return ebeanServer.find(ProjectServer.class).findList();
    }
}
