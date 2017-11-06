package cn.yfjz.core.util;

import cn.yfjz.core.sys.domain.PermModel;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liwj on 15/7/28.
 */
public class PermUtil {
    private static boolean isExistChildPerm(Element e){
        List list = e.elements();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }
    private static List<PermModel> getSubPerm(Element e, PermModel t){
        List<PermModel> list = new ArrayList<PermModel>();
        Iterator it = e.elementIterator();
        while(it.hasNext()){
            Element element = (Element)it.next();
            PermModel model = new PermModel();
            model.setPermname(element.attributeValue("name"));
            model.setPermid(element.attributeValue("value"));
            list.add(model);
        }
        return list;
    }
    public static List<PermModel> getAllPermsForRole(ServletContext servletContext) {
        List<PermModel> list = new ArrayList<PermModel>();
        String path = servletContext.getRealPath("/WEB-INF/classes");
        Document doc = XmlUtil.load(path + "/perm.xml");
        Element root = doc.getRootElement();
        Iterator itex = root.elementIterator();
        while(itex.hasNext()){
            Element element = (Element)itex.next();
            PermModel model = new PermModel();
            model.setPermname(element.attributeValue("name"));
            model.setPermid(element.attributeValue("value"));
            model.setImg(element.attributeValue("img"));
            if(isExistChildPerm(element)){
                model.setSubPerms(getSubPerm(element, model));
            }
            if(model.getSubPerms()!=null&&model.getSubPerms().size()>0){
                list.add(model);
            }
        }
        return list;
    }
}
