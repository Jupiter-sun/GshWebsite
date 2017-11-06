package cn.yfjz.xg.directive;

import cn.yfjz.core.freemarker.DirectiveUtils;
import cn.yfjz.xg.common.service.SelectService;
import cn.yfjz.xg.domain.SelectKey;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * Created by liwj on 16/9/27.
 */
public class CommonDirective implements TemplateDirectiveModel {
    @Autowired
    private SelectService selectService;
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = DirectiveUtils.getString("type", params);
        String parentId = DirectiveUtils.getString("parentId", params);
        SelectKey key = SelectKey.toKey(type);
        List result = selectService.queryCommon(key, parentId);
        Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
        paramWrap.put(DirectiveUtils.OUT_LIST, DEFAULT_WRAPPER.wrap(result));
        Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
        body.render(env.getOut());
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }
}
