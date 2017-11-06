package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/8/17.
 */
@Entity
@Table(name = "xg_content_attachment")
public class ContentAttachment extends IModel {
    @ManyToOne
    private Content content;
    @Column(length = 100)
    private String name;
    @Column(length = 255)
    private String path;
    @Column(length = 4)
    private Long priority;
    @Column(length = 100)
    private String filename;
    @Column
    private Long downloadCount;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }
}
