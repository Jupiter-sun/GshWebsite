package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.domain.Job;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.sys.service.JobService;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

/**
 * Created by liwj on 16/9/30.
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;


    @RequestMapping("/list")
    public String list(){
        return "/job/list";
    }

    @RequestMapping("/page")
    public String getPage(HttpServletRequest request){
        Pager page = jobService.queryPage(this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/job/page";
    }


    @RequestMapping("/toAdd")
    public String toAdd(ModelMap m, HttpServletRequest request){
        return "/job/add";
    }

    @RequestMapping("/add")
    public String add(Job job, ModelMap m){
        try {
            jobService.add(job);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/toEdit")
    public String toEdit(String ids, ModelMap m,HttpServletRequest request){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Job job = jobService.findById(ids);
                m.put("job", job);
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
        return "/job/edit";
    }
    @RequestMapping("/edit")
    public String edit(Job job, ModelMap m){
        try {
            jobService.edit(job);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }


    @RequestMapping("/remove")
    @ResponseBody
    public Msg remove(String[] ids, ModelMap m){
        try {
            if(ArrayUtils.isNotEmpty(ids)){
                jobService.remove(ids);
                msg.setSuccessMsg("删除成功");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }

}
