package cn.yfjz.website.service;

import cn.yfjz.website.dto.ProjectServerDTO;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
public interface ProjectService {
    List<ProjectServerDTO> queryAll();
}
