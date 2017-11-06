package cn.yfjz.core.sys.controller;

import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.ExcelReader;
import cn.yfjz.core.util.ExcelWriter;
import cn.yfjz.core.util.Identities;
import cn.yfjz.core.sys.service.ImportService;
import cn.yfjz.xg.domain.ImportModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/11/21.
 */
@Controller
@RequestMapping("/data_import")
public class DataImportController extends BaseController{
    @Autowired
    private ImportService importService;

    /**
     * 导入初始化
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request, String id){
        if(StringUtils.isNotEmpty(id)){
            ImportModel dto = importService.findById(id);
            request.setAttribute("importDTO", dto);
        }
        else{
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "id不能为空");
            request.setAttribute("msg", msg);
        }
        if(request.getParameter("t")!=null&&request.getParameter("t")!="true"){
            return "/dataimport/firstT";//返回弹窗页面
        }
        return "/dataimport/first";
    }

    /**
     * 下载导入模版
     */
    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response){
        String importId = request.getParameter("id");
        List columns = importService.queryColumns(importId);
        ExcelWriter.exportTemplate(response, columns);
    }

    /**
     * 解析上传的excel，导入数据到临时表，验证数据
     */
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile files){
        try {
            String importId = request.getParameter("id");
            ImportModel dto = importService.findById(importId);
            //导入列
            List columns = importService.queryColumns(importId);
            //批次
            String pc = Identities.id();
            //解析上传的excel
            ExcelReader excelReader = new ExcelReader(files.getInputStream(), files.getOriginalFilename());
            //获取excel标题（第一行）
            List<String> titles = excelReader.getTitle();
            //获取excel中数据
            List<List<String>> content = excelReader.getContent();
            List<String> cList = new ArrayList<String>();
            List<Map> errorList = importService.saveTemp(dto,columns,pc,titles,content, cList);
            SecurityUtils.getSubject().getSession().setAttribute(importId + "_header", cList);
            request.setAttribute("importDTO", dto);
            request.setAttribute("columns", columns);
            request.setAttribute("pc", pc);
            request.setAttribute("resultCount", content.size());
            request.setAttribute("errorList", errorList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(request.getParameter("t")!=null&&request.getParameter("t")!="true"){
            return "/dataimport/nextT";//返回弹窗页面
        }
        return "/dataimport/next";
    }

    /**
     * 导入数据到正式表
     */
    @RequestMapping("/importData")
    public String importData(HttpServletRequest request, String id, String pc){
        ImportModel dto = importService.findById(id);
        request.setAttribute("importDTO", dto);
        request.setAttribute("pc", pc);
        List<String> cList = (List<String>)SecurityUtils.getSubject().getSession().getAttribute(id + "_header");
        try {
            String[] result = importService.saveData(id, pc, cList);
            request.setAttribute("result", result);
        } catch (Exception e) {
            request.setAttribute("result", new String[]{e.getMessage()});
            e.printStackTrace();
        }
        if(request.getParameter("t")!=null&&request.getParameter("t")!="true"){
            return "/dataimport/resultT";//返回弹窗页面
        }
        return "/dataimport/result";
    }

}
