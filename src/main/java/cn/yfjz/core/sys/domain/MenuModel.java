package cn.yfjz.core.sys.domain;

import java.util.List;

public class MenuModel {
	private String id;
    private String name;
    private String img;
    private String url;
    private String perms;
    private boolean hasChildren= false ;
    private List<MenuModel> subMenus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public List<MenuModel> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuModel> subMenus) {
		this.subMenus = subMenus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
