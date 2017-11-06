package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.sys.service.ClassListService;
import cn.yfjz.core.sys.service.DeptService;
import cn.yfjz.core.sys.service.MajorService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static cn.yfjz.core.util.CodeConstant.RESULT_TAB_HTML;
/**
 * 
 * @author liuy
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController{
	@Autowired
	private DeptService deptService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private ClassListService classListService;
	
	@RequestMapping("/main")
    public String mian(ModelMap m){
        return "/dept/main";
    }

    @RequestMapping("/deptList")
    public String deptList(Dept dept,HttpServletRequest request){
        dept.setFather(dept);
        Pager page = deptService.queryPage(dept,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/dept/deptList";
    }
    
    @RequestMapping("/dept/toAdd")
    public String toAddDept(HttpServletRequest request,String pId){
    	if(StringUtils.isEmpty(pId)){
    		pId = CodeConstant.DEPT_ROOT_DEFAULT_ID;
    	}
    	Long pid = Long.parseLong(pId);
    	request.setAttribute("pId", pid);
        return "/dept/deptAdd";
    }
    @RequestMapping("/dept/add")
    public String addDept(Dept dept,String pId, ModelMap m){
        try {
        	Long pid = Long.parseLong(pId);
        	Dept pDept = new Dept();
        	pDept.setId(pid);
        	dept.setFather(pDept);
            String message = deptService.add(dept);
            if(StringUtils.isEmpty(message)){
            	msg.setSuccessMsg("新增成功");
            }else{
            	msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }

    @RequestMapping("/dept/toEdit")
    public String toEditDept(String ids, ModelMap m){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Long id = Long.parseLong(ids);
                Dept dept = deptService.findById(id);
                m.put("dept", dept);
                msg.setSuccessMsg("ok");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/dept/deptEdit";
    }
    @RequestMapping("/dept/edit")
    public String editDept(Dept dept, ModelMap m,HttpServletRequest request){
        try {
            String s=request.getParameter("fatherid");
            Dept dept1=deptService.findByName(s);
            dept.setFather(dept1);
            deptService.edit(dept);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }
    @RequestMapping("/dept/remove")
    @ResponseBody
    public Msg removeDept(String[] ids, ModelMap m){
        try {
            deptService.remove(ids);
            msg.setSuccessMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }
    
    
    
    @RequestMapping("/majorList")
    public String majorList(Major major,HttpServletRequest request){
        Pager page = majorService.queryPage(major,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
		return "/dept/majorList";
    }
    @RequestMapping("/major/toAdd")
    public String toAddMajor(HttpServletRequest request){
        return "/dept/majorAdd";
    }
    @RequestMapping("/major/add")
    public String addMajor(Major major,ModelMap m){
        try {
            String message = majorService.add(major);
            if(StringUtils.isEmpty(message)){
            	msg.setSuccessMsg("新增成功");
            }else{
            	msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }

    @RequestMapping("/major/toEdit")
    public String toEditMajor(String ids, ModelMap m){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Long id = Long.parseLong(ids);
                Major major = majorService.findById(id);
                m.put("major", major);
                msg.setSuccessMsg("ok");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/dept/majorEdit";
    }
    @RequestMapping("/major/edit")
    public String editMajor(Major major, ModelMap m){
        try {
            majorService.edit(major);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }
    
    @RequestMapping("/major/remove")
    @ResponseBody
    public Msg removeMajor(String[] ids, ModelMap m){
        try {
            majorService.remove(ids);
            msg.setSuccessMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }
    
    @RequestMapping("/classList")
    public String classList(ClassList classList,HttpServletRequest request){
        Pager page = classListService.queryPage(classList,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
		return "/dept/classList";
    }
    @RequestMapping("/class/toAdd")
    public String toAddClass(HttpServletRequest request){
        return "/dept/classAdd";
    }
    @RequestMapping("/class/add")
    public String addClass(ClassList classList,ModelMap m){
        try {
            String message = classListService.add(classList);
            if(StringUtils.isEmpty(message)){
            	msg.setSuccessMsg("新增成功");
            }else{
            	msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }

    @RequestMapping("/class/toEdit")
    public String toEditClass(String ids, ModelMap m){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Long id = Long.parseLong(ids);
                ClassList classList = classListService.findById(id);
                m.put("class", classList);
                msg.setSuccessMsg("ok");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/dept/classEdit";
    }
    @RequestMapping("/class/edit")
    public String editClass(ClassList classList, ModelMap m){
        try {
        	classListService.edit(classList);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TAB_HTML;
    }
    
    @RequestMapping("/class/remove")
    @ResponseBody
    public Msg removeClass(String[] ids, ModelMap m){
        try {
            classListService.remove(ids);
            msg.setSuccessMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }

    @RequestMapping("/getDepts")
    @ResponseBody
    public List<Map<String, Object>> getDepts(String fid, HttpServletRequest request, ModelMap m){
        return deptService.tree(fid);
    }


}
