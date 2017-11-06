package cn.yfjz.core.sys.domain;

import java.util.List;

/**
 * Created by liwj on 15/7/28.
 */
public class PermModel {
    private String permid;
    private String permname;
    private String img;
    private List<PermModel> subPerms;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<PermModel> getSubPerms() {
        return subPerms;
    }

    public void setSubPerms(List<PermModel> subPerms) {
        this.subPerms = subPerms;
    }
    public String getPermid() {
        return permid;
    }
    public void setPermid(String permid) {
        this.permid = permid;
    }
    public String getPermname() {
        return permname;
    }
    public void setPermname(String permname) {
        this.permname = permname;
    }
}
