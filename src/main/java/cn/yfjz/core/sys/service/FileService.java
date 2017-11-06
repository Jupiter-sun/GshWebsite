package cn.yfjz.core.sys.service;


import cn.yfjz.core.sys.domain.File;

import java.util.List;

/**
 * Created by fph on 2017/8/8.
 */
public interface FileService {
    public List<File> findByObjectId(Long id);
    public List<File> findByWorktId(Long id);
    public List<File> findByApproveId(Long id);
}
