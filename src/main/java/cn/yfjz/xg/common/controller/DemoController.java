package cn.yfjz.xg.common.controller;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.xg.common.service.Demoservice;
import cn.yfjz.xg.domain.Demo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/9/27.
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
    @Autowired
    private Demoservice demoservice;

    @RequestMapping("/index")
    public String index() {
        return "/demo/index";
    }

    @RequestMapping("/demo")
    public String demo() {
        return "/demo/demo";
    }
    //在线编辑execl用json数据传到后台解析
    @RequestMapping("/add")
    @ResponseBody
    public Msg add(HttpServletRequest request) {
        String param = request.getParameter("param");
        try {
            JSONObject json = JSONObject.fromObject(param);
            List<Map<String, String>> list = json.getJSONArray("data");
            for (int i = 0; i < list.size(); i++) {
                Demo demo=new Demo();
                demo.setLd(list.get(i).get("ld"));
                demo .setLc(list.get(i).get("lc"));
                demo.setFjh(list.get(i).get("fjh"));
                demo.setZj(list.get(i).get("zj"));
                demo.setXm(list.get(i).get("xm"));
                demo.setXb(list.get(i).get("xb"));
                demo.setStarttime(list.get(i).get("starttime"));
                demo.setEndtime(list.get(i).get("endtime"));
                demo.setTel(list.get(i).get("tel"));
                demo.setZjh(list.get(i).get("zjh"));
                demo.setFzjz(list.get(i).get("fzjz"));
                demo.setBz(list.get(i).get("bz"));
                demoservice.add(demo);
            }
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }
    // 解析本地的execl表格
    @RequestMapping("/read")
    public String read() {
        //得到表格中所有的数据
        List<Demo> listExecl = demoservice.getAllByExecl("d://test//b//a.xls");
        for (Demo demo : listExecl) {
            if (demoservice.isExist(demo.getXm())) {
                demoservice.add(demo);
            } else {
                demoservice.edit(demoservice.findByxm(demo.getXm()));
            }
        }
        return "demo/index";
    }
}








