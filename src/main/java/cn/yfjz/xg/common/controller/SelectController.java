package cn.yfjz.xg.common.controller;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.sys.domain.XgCode;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.xg.common.service.SelectService;
import cn.yfjz.xg.domain.SelectKey;
import cn.yfjz.xg.domain.SelectModel;
import cn.yfjz.xg.domain.Xueyuan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.List;

/**
 * Created by liwj on 16/9/23.
 */
@Controller
@RequestMapping("/select")
public class SelectController extends BaseController {

    @Autowired
    private SelectService selectService;
    private UserService userService;

    @RequestMapping("/xy")
    public String xylist(ModelMap m, SelectModel selectModel){
        List<Xueyuan> result = selectService.queryXueyuan();
//        if(RoleUtil.isSysadmin()){
//            result = cacheService.queryXueyuan();
//        }
//        else {
//            result = cacheService.queryXyByRole();
//        }
//
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/xy";
    }

    @RequestMapping("/zy")
    public String zylist(ModelMap m, SelectModel selectModel){
        List<Major> result = selectService.queryMajor(selectModel.getXyId(), selectModel.getNj());
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/zy";
    }
    @RequestMapping("/bj")
    public String bjlist(ModelMap m, SelectModel selectModel){
        List<ClassList> result = selectService.queryClasslist(selectModel.getXyId(), selectModel.getNj(), selectModel.getZyId());
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/bj";
    }

    @RequestMapping("/common")
    public String common(ModelMap m, SelectModel selectModel) throws Exception{
        String value = selectModel.getValue();
        if(StringUtils.isNotEmpty(value)){
            value = URLDecoder.decode(value, "utf-8");
            selectModel.setValue(value);
        }
        List<XgCode> result = selectService.queryCommon(selectModel.getKey(), selectModel.getParentId());
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/common";
    }

    @RequestMapping("/nj")
    public String njlist(ModelMap m, SelectModel selectModel){
        List<String> result = selectService.queryNj();
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/nj";
    }
    @RequestMapping("/njJson")
    @ResponseBody
    public List njListJson(){
        List<String> result = selectService.queryNj();
        return result;
    }
    @RequestMapping("/demo")
    public String demo(){
        return "/select/demo";
    }
    @RequestMapping("/area")
    public String area(ModelMap m, SelectModel selectModel){
        List result = selectService.queryCommon(SelectKey.code, "20004");
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/area";
    }

    @RequestMapping("/getArea")
    @ResponseBody
    public List getArea(String parentId){
        List result = selectService.queryCommon(SelectKey.code, parentId);
        return result;
    }

    @RequestMapping("/xn")
    public String xnlist(ModelMap m, SelectModel selectModel)throws Exception{
        String value = selectModel.getValue();
        if(StringUtils.isNotEmpty(value)){
            value = URLDecoder.decode(value, "utf-8");
            selectModel.setValue(value);
        }
        List result = selectService.queryXn();
        m.addAttribute("list", result);
        m.addAttribute("selectModel", selectModel);
        return "/select/xn";
    }
}
