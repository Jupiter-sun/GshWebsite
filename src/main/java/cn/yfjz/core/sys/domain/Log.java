package cn.yfjz.core.sys.domain;

import javax.persistence.*;

/**
 * Created by liwj on 2016/5/16.
 */
@Entity
@Table(name = "p_log")
public class Log extends IModel {
    @Column(length = 50)
    private String category;
    @Column(length = 10)
    private String logLevel;
    @Column(length = 50)
    private String title;
    @Column(length = 500)
    private String content;
    @Column(length = 20)
    private String ip;
    @Column(length = 50)
    private String action;
    @Column(length = 50)
    private String client;

    @ManyToOne @JoinColumn(name = "user_id")
    private User operUser;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public User getOperUser() {
        return operUser;
    }

    public void setOperUser(User operUser) {
        this.operUser = operUser;
    }
}
