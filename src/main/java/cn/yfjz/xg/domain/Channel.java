package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.IModel;
import cn.yfjz.core.sys.domain.File;

import javax.persistence.*;

/**
 * Created by liwj on 16/8/17.
 */
@Entity
@Table(name = "xg_channel")
public class Channel extends IModel {
    @ManyToOne @JoinColumn(name="father_id")
    private Channel parent;
    @Column(length = 4)
    private Long priority;
    @Column(length = 2)
    private String isDisplay;
    @Column(length = 100)
    private String name;
    @Column(length = 255)
    private String link;
    @Column(length = 2)
    private String isBlank;
    @ManyToOne
    @JoinColumn(name = "icon_id")
    private File icon;

    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(String isBlank) {
        this.isBlank = isBlank;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }
}
