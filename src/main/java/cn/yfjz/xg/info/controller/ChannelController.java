package cn.yfjz.xg.info.controller;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import cn.yfjz.xg.domain.Channel;
import cn.yfjz.xg.info.service.ChannelService;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

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
 * @Date：2016年8月18日 
 * @Time：下午3:31:01 
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {
    @Autowired
    private ChannelService channelService;
    @RequestMapping("/list")
    public String list(ModelMap m){
    	Long pid = Long.parseLong("1");
        Channel channel = channelService.findById(pid);
        m.put("channel",channel);
        return "/channel/list";
    }
    
    @RequestMapping("/getChannels")
    @ResponseBody
    public List getChannels(String fid,HttpServletRequest request){
        return channelService.tree(fid);
    }
    
    @RequestMapping("/page")
    public String getPage(Long channelid,HttpServletRequest request){
        String code = request.getParameter("code");
    	String name = request.getParameter("name");
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	if(StringUtils.isNotEmpty(name)) paramMap.put("name", name);
    	if(StringUtils.isNotEmpty(code)) paramMap.put("code", code);
        Pager page = channelService.queryPage(paramMap,channelid,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/channel/page";
    }
    
    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request,String pId){
    	if(StringUtils.isEmpty(pId)){
    		pId = CodeConstant.CHANNEL_ROOT_DEFAULT_ID;
    	}
    	Long pid = Long.parseLong(pId);
    	Channel channelPre = channelService.findById(pid);
    	request.setAttribute("pId", pid);
    	request.setAttribute("channelPre", channelPre);
        return "/channel/add";
    }
    
    @RequestMapping("/add")
    public String add(Channel channel,String pId, ModelMap m){
        try {
        	Long pid = Long.parseLong(pId);
        	Channel preChannel = new Channel();
        	preChannel.setId(pid);
        	channel.setParent(preChannel);
            channelService.add(channel);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }
    
    @RequestMapping("/toEdit")
    public String toEdit(String ids, ModelMap m){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Long id = Long.parseLong(ids);
                Channel channel = channelService.findById(id);
                m.put("channel", channel);
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
        return "/channel/edit";
    }
    @RequestMapping("/edit")
    public String edit(Channel channel, ModelMap m){
        try {
        	channelService.edit(channel);
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
            	channelService.remove(ids);
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
