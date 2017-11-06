package cn.yfjz.api;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.JsonUtils;
import cn.yfjz.core.util.Pager;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouc on 2016/12/16.
 */

@Controller
@RequestMapping("/api/news")
public class ApiNewsController  extends BaseController {

    @RequestMapping("/sync")
    @ResponseBody
    public Map login(HttpServletRequest request, HttpServletResponse response){

        Map map = JsonUtils.success();
        try {

        }catch (Exception e){
            map = JsonUtils.error("-1","同步出错，"+e.getMessage());
        }
        return map;
    }

}
