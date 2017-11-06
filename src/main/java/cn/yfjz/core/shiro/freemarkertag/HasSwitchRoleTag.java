package cn.yfjz.core.shiro.freemarkertag;

import cn.yfjz.core.shiro.ShiroUser;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by liwj on 16/8/9.
 */
public class HasSwitchRoleTag extends SecureTag {
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody();
        if (show) {
            renderBody(env, body);
        }
    }
    protected boolean showTagBody() {
        Subject subject = getSubject();
        if(subject!=null && subject.getPrincipal()!=null){
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            if(user.getRoles()!=null && user.getRoles().size()>1){
                return true;
            }
        }
        return false;
    }
}