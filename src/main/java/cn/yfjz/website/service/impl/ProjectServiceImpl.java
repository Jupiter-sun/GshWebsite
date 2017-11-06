package cn.yfjz.website.service.impl;

import cn.yfjz.core.sys.domain.File;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.website.domain.ProjectServer;
import cn.yfjz.website.dto.ProjectServerDTO;
import cn.yfjz.website.service.ProjectService;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private EbeanServer ebeanServer;

    @Override
    public List<ProjectServerDTO> queryAll(){
        List<ProjectServer> projectServers = ebeanServer.find(ProjectServer.class).findList();
        List<ProjectServerDTO> projectServerDTO = new ArrayList<>();

        List<Expression> params = new ArrayList<>();
        Map<String,String> paramMap = new HashMap();
        for (ProjectServer dto:projectServers){
            String filter = dto.getFilter();
            Query<File> query = QueryUtils.createQuery(File.class,ebeanServer,"find file",paramMap);
            List<File> list = query.where().in("object_id",filter).findList();
            for (int i=0;i<list.size();i++){
                ProjectServerDTO vo = new ProjectServerDTO();
                vo.setId(dto.getId());
                vo.setFiles(list);
                vo.setName(dto.getName());
                projectServerDTO.add(vo);
            }
        }
        return projectServerDTO;
    }
}
