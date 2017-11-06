package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.IModel;
import cn.yfjz.core.sys.domain.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwj on 16/8/17.
 */
@Entity
@Table(name = "xg_content")
public class Content extends IModel {
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private User user;
    @Column(length = 4)
    private Long topLevel;
    @Column(columnDefinition = "DATE")
    private Date sortDate;
    @Column(length = 2)
    private String isRecommend;
    @Column(length = 2)
    private String status;
    @Column
    private Long viewsDay;
    @Column(length = 150)
    private String title;
    @Column(length = 150)
    private String shortTitle;
    @Column(columnDefinition="CLOB")
    private String txt;
    @Column(length = 100)
    private String author;
    @Column(length = 100)
    private String origin;
    @Column(length = 255)
    private String originUrl;
    @Column(length = 255)
    private String description;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Long topLevel) {
        this.topLevel = topLevel;
    }

    public Date getSortDate() {
        return sortDate;
    }

    public void setSortDate(Date sortDate) {
        this.sortDate = sortDate;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getViewsDay() {
        return viewsDay;
    }

    public void setViewsDay(Long viewsDay) {
        this.viewsDay = viewsDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
