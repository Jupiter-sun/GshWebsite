package cn.yfjz.xg.info.controller;

import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.xg.domain.Channel;
import cn.yfjz.xg.domain.Content;
import cn.yfjz.xg.info.service.ChannelService;
import cn.yfjz.xg.info.service.ContentService;
import net.sf.json.JSONArray;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yuanye
 * @Date：2016年8月23日
 * @Time：下午2:51:45
 */
@Controller
@RequestMapping("/content")
public class ContentController extends BaseController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private  UserService userService;
    @RequestMapping("/list")
    public String list(ModelMap m){
    	Long pid = Long.parseLong("1");
        Channel channel = channelService.findById(pid);
        m.put("channel",channel);
        return "/content/list";
    }

    @RequestMapping("/getChannels")
    @ResponseBody
    public String getChannels(String fid,HttpServletRequest request,ModelMap m){
        List list = new ArrayList();
        try{
            list =  channelService.tree(fid);
        }catch(Exception e){
            msg.setErrorMsg("-1","操作失败！");
        }
        m.put("msg", msg);
        m.put("channels",list);
        JSONArray jsonObject  = JSONArray.fromObject(list);
        return jsonObject.toString();
    }

    @RequestMapping("/page")
    public String getPage(Long channelid,HttpServletRequest request){
        String title = request.getParameter("title");//添加查询的条件[JIE.LI 2016/11/4]
        String code = request.getParameter("code");
    	String name = request.getParameter("name");
    	Map<String,Object> paramMap = new HashMap<String,Object>();
        if(StringUtils.isNotEmpty(title)){paramMap.put("title",title);}
    	if(StringUtils.isNotEmpty(name)) paramMap.put("name", name);
    	if(StringUtils.isNotEmpty(code)) paramMap.put("code", code);
        Pager page = contentService.queryPage(paramMap,channelid,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/content/page";
    }

    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request,String pId){
    	if(StringUtils.isEmpty(pId)){
    		pId = CodeConstant.CHANNEL_ROOT_DEFAULT_ID;
    	}
    	Long pid = Long.parseLong(pId);
    	Channel channel = channelService.findById(pid);
    	request.setAttribute("pId", pid);
    	request.setAttribute("channel", channel);
        return "/content/add";
    }

    @RequestMapping("/add")
    public String add(Content content,ModelMap m){
    	ShiroUser currentUser = UserAuthUtils.getCurrentUser();
    	User user = userService.findById(currentUser.getId());
    	/*content.setUser(user);
    	content.setAuthor(user.getTruename());*/
		try {
			contentService.add(content);
			msg.setSuccessMsg("新增成功");
		} catch (Exception e) {
			msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
			e.printStackTrace();
		}
		m.put("msg", msg);
		return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request,String ids){
    	Long id = Long.parseLong(ids);
    	Content content = contentService.findById(id);
    	request.setAttribute("content", content);
        return "/content/edit";
    }

    @RequestMapping("/edit")
    public String edit(Content content, ModelMap m){
        try {
        	contentService.edit(content);
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
            	contentService.remove(ids);
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

    @RequestMapping("/toView")
    public String toView(HttpServletRequest request,String id,String type){
    	Content content = contentService.findById(Long.parseLong(id));
    	request.setAttribute("content", content);
    	if(StringUtils.isNotEmpty(type)&&"content".equals(type)){
    		return "/content/viewContent";
    	}else{
    		return "/content/view";
    	}
    }

    @RequestMapping("/contentList")
    public String contentMoreList(HttpServletRequest request, String id) {
    	if(StringUtils.isEmpty(id)){
    		id = CodeConstant.CONTENT_ROOT_DEFAULT_ID;
    	}
    	Map<String,Object> paramMap = new HashMap<String,Object>();
        Pager page = contentService.queryPage(paramMap,Long.parseLong(id),this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/contentMoreList";
    }
}
