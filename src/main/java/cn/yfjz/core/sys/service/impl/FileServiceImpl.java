package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.File;
import cn.yfjz.core.sys.service.FileService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fph on 2017/8/8.
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public List<File> findByObjectId(Long id) {
        return ebeanServer.find(File.class).where().eq("object_id",id).findList();
    }

    @Override
    public List<File> findByWorktId(Long id) {
        return ebeanServer.find(File.class).where().eq("work_id",id).findList();
    }

    @Override
    public List<File> findByApproveId(Long id) {
        return ebeanServer.find(File.class).where().eq("approve_id",id).findList();
    }
}
