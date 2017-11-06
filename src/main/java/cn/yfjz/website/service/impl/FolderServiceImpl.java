package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.Folder;
import cn.yfjz.website.service.FolderService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/5.
 */
@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private EbeanServer ebeanServer;

    public List<Folder> findAll(){
        return ebeanServer.find(Folder.class).findList();
    }
}
