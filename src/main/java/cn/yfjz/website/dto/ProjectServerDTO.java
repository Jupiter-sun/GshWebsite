package cn.yfjz.website.dto;

import cn.yfjz.core.sys.domain.File;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
public class ProjectServerDTO{
    private Long id;

    private String name;

    private List<File> files;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
