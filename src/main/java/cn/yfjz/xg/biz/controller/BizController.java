package cn.yfjz.xg.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.xg.biz.service.XgBizService;
import cn.yfjz.xg.domain.XgBiz;
import cn.yfjz.xg.domain.XgBizField;

@Controller
@RequestMapping("/biz")
public class BizController extends BaseController{
	@Autowired
    private XgBizService xgBizService;
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
	     return "/biz/list";
	}
	
	@RequestMapping("/page")
    public String page(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
        Pager page = xgBizService.queryPage(paramMap,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/biz/page";
    }
	
	@RequestMapping("/field/list")
	public String fieldList(HttpServletRequest request,String id){
		request.setAttribute("id", id);
	    return "/biz/field/list";
	}
	
	@RequestMapping("/field/page")
	public String fieldPage(HttpServletRequest request,String id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("biz_id", id);
        Pager page = xgBizService.queryXgBizFieldPage(paramMap,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
	    return "/biz/field/page";
	}
	
	@RequestMapping("/field/toAdd")
    public String toAddField(HttpServletRequest request,String id){
		request.setAttribute("id", id);
        return "/biz/field/add";
    }
	
	@RequestMapping("/field/add")
    public String addField(XgBizField xgBizField ,ModelMap m){
        try {
        	xgBizService.addXgBizField(xgBizField);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return CodeConstant.RESULT_TOPIC_HTML;
    }
	
	@RequestMapping("/field/toEdit")
    public String toEditField(HttpServletRequest request,String ids){
    	XgBizField xgBizField = xgBizService.findXgBizFieldById(ids);
    	request.setAttribute("xgBizField", xgBizField);
        return "/biz/field/edit";
    }
	
	@RequestMapping("/field/edit")
    public String editField(XgBizField xgBizField ,ModelMap m){
        try {
        	xgBizService.updateXgBizField(xgBizField);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return CodeConstant.RESULT_TOPIC_HTML;
    }
	
	@RequestMapping("/field/remove")
    @ResponseBody
    public Msg removeField(String[] ids, ModelMap m){
        try {
            if(ArrayUtils.isNotEmpty(ids)){
            	xgBizService.removeXgBizField(ids);
                msg.setSuccessMsg("删除成功");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "删除失败！");
        }
        return msg;
    }
	
	@RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request,String id){
    	XgBiz xgBiz = xgBizService.findXgBizById(id);
    	request.setAttribute("xgBiz", xgBiz);
        return "/biz/edit";
    }
	
	@RequestMapping("/edit")
    public String edit(XgBiz xgBiz ,ModelMap m){
        try {
        	xgBizService.updateXgBiz(xgBiz);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return CodeConstant.RESULT_TOPIC_HTML;
    }
	
	/**
     * 进入学工队伍自定义列表
     * @param request
     * @param bizid
     * @param callback
     * @return
     */
	@RequestMapping("/main")
    public String main(HttpServletRequest request, String bizid, String fundid,String callback){
        request.setAttribute("bizid", bizid);
        request.setAttribute("callback", callback);
        request.setAttribute("fundid", fundid);
        List<?> unselected = xgBizService.findUnselect(bizid);
        request.setAttribute("unselected", unselected);
        List<?> selected = xgBizService.findSelect("XG_ASS_FDY");
        request.setAttribute("selected", selected);
        request.setAttribute("bizid", bizid);
        request.setAttribute("callback", callback);
        return "/biz/main";
    }

	/**
     * 保存学工队伍自定义列表
     * @param request
     * @param bizid
     * @param selected
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Msg save(HttpServletRequest request, String bizid, String[] selected){
    	List xgBizFields = xgBizService.findXgBizFieldByBizId(bizid);
    	ShiroUser currentUser = UserAuthUtils.getCurrentUser();
    	xgBizService.save(xgBizFields, currentUser.getId(), bizid, selected);
        msg.setSuccessMsg("保存成功！");
        return msg;
    }
}
