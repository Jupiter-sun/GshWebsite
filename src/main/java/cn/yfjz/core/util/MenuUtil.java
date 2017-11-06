package cn.yfjz.core.util;

import cn.yfjz.core.sys.domain.MenuModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MenuUtil {
	private static boolean isExistChildPerm(Element e){
		List list = e.elements();
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}


	public static List<MenuModel> getAllMenu(ServletContext servletContext) {
		List<MenuModel> list = new ArrayList<MenuModel>();
		String path = servletContext.getRealPath("/WEB-INF");
		Document doc = XmlUtil.load(path + "/menu-config.xml");
		Element root = doc.getRootElement();
		Iterator itex = root.elementIterator();
		while(itex.hasNext()){
			Element element = (Element)itex.next();
			MenuModel model = new MenuModel();
			model.setId(element.attributeValue("id"));
			model.setName(element.attributeValue("name"));
			model.setImg(element.attributeValue("img"));
			if(isExistChildPerm(element)){
				model.setSubMenus(getSubMenu(element, model));
				if(model.getSubMenus()!=null&&model.getSubMenus().size()>0){
					list.add(model);
				}
			}
			else{
				model.setUrl(element.attributeValue("url"));
				model.setPerms(element.attributeValue("perms"));
				filterPerm(model, list);
			}

		}
		return list;
	}
	
	
	private static List<MenuModel> getSubMenu(Element e, MenuModel t){
		List<MenuModel> list = new ArrayList<MenuModel>();
		Iterator it = e.elementIterator();
		while(it.hasNext()){
			Element element = (Element)it.next();
			MenuModel model = new MenuModel();
			model.setId(element.attributeValue("id"));
			model.setName(element.attributeValue("name"));
			model.setUrl(element.attributeValue("url"));
			model.setPerms(element.attributeValue("perms"));
			model.setImg(element.attributeValue("img"));
			filterPerm(model, list);
		}
		return list;
	}

	private static void filterPerm(MenuModel model, List list){
		Subject currentUser = SecurityUtils.getSubject();
		//多个权限用逗号分割
		if(StringUtils.isEmpty(model.getPerms())){
			list.add(model);
		}else{
			String[] perms = model.getPerms().split(",");
			//只要包含其中的一个权限则显示出来
			for(String perm:perms){
				if(currentUser.isPermitted(perm)){
					list.add(model);
					break;
				}
			}
		}
	}
}
