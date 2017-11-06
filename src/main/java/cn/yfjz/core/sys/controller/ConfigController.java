package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.domain.Config;
import cn.yfjz.core.sys.service.ConfigService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

/**
 * Created by liwj on 2016/5/18.
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    @RequestMapping("/list")
    public String main(){
        return "/config/list";
    }
    @RequestMapping("/page")
    public String getPage(HttpServletRequest request){
        Pager page = configService.queryPage(this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/config/page";
    }
    @RequestMapping("/toEdit")
    public String toEdit(String ids, ModelMap m, HttpServletRequest request){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Config config = configService.findById(ids);
                m.put("config", config);
                msg.setSuccessMsg("ok");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"未找到符合条件的数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/config/edit";
    }
    @RequestMapping("/edit")
    public String edit(Config config, ModelMap m){
        try {
            configService.edit(config);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }
}
