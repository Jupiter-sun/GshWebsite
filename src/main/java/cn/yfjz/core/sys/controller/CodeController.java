package cn.yfjz.core.sys.controller;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.yfjz.core.sys.service.CodeKindService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yfjz.core.sys.domain.CodeItem;
import cn.yfjz.core.sys.domain.CodeKind;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.sys.service.CodeItemService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;

/*
* 数据字典  左边数据存储在kind表格中 右边数据存储在item表格中
* */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {
    @Autowired
    private CodeKindService kindService;
    @Autowired
    private CodeItemService itemService;

    @RequestMapping("/kindtree")  //树结构
    @ResponseBody
    public List kindTree(String id) {
        return kindService.codeKindTree(id);
    }

    @RequestMapping("/list")    //list展示页面
    public String list(ModelMap m) {
        return "/code/list";
    }

    @RequestMapping("/kind/page")  //左边页面
    public String getKindPage(String id, HttpServletRequest request) {
        Pager page = kindService.queryPage(id, this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page", page);
        return "/code/kind/page";
    }

    @RequestMapping("/item/page")   //右边页面
    public String getPage(String kindId, HttpServletRequest request) {
        Pager page = itemService.queryPageByKind(kindId, this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page", page);
        return "/code/item/page";
    }


    @RequestMapping("/kind/toAdd")
    public String toAddKind(HttpServletRequest request, String pId) {
        try {
            if (StringUtils.isNotEmpty(pId)) {
                pId = URLDecoder.decode(pId, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setAttribute("pId", pId);
        return "/code/kind/add";
    }

    @RequestMapping("/kind/add")
    public String add(CodeKind kind, ModelMap m) {
        try {
            kindService.add(kind);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setSuccessMsg("新增失败！");
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/kind/validate")   //判断当前id是否存在
    @ResponseBody
    public Msg validateKind(String id) {
        CodeKind kind = kindService.findById(id);
        if (kind != null) {
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "当前代码ID[" + id + "]已存在");
        } else {
            msg.setSuccessMsg("ok");
        }
        return msg;
    }

    @RequestMapping("/item/toAdd")
    public String toAdd(HttpServletRequest request, String kindId, String parentId) {
        request.setAttribute("kindId", kindId);
        request.setAttribute("parentId", parentId);
        return "/code/item/add";
    }

    @RequestMapping("/item/validate")
    @ResponseBody
    public Msg validateItem(String id) {
        CodeItem item = itemService.findById(id);
        if (item != null) {
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "当前代码ID[" + id + "]已存在");
        } else {
            msg.setSuccessMsg("ok");
        }
        return msg;
    }

    @RequestMapping("/item/add")
    public String add(CodeItem item, ModelMap m) {
        try {
            itemService.add(item);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrmsg("新增失败！");
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/kind/toEdit")
    public String toEditKind(String ids, ModelMap m) {
        try {
            if (StringUtils.isNotEmpty(ids)) {
                CodeKind kind = kindService.findById(ids);
                m.put("kind", kind);
                msg.setSuccessMsg("kind");
            } else {
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/code/kind/edit";
    }

    @RequestMapping("/kind/edit")
    public String editkind(CodeKind kind, ModelMap m) {
        try {
            kindService.edit(kind);
            msg.setSuccessMsg("操作成功！");
        } catch (Exception e) {
            msg.setErrmsg("操作失败！");
            e.printStackTrace();
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/item/toEdit")
    public String toEditItem(String ids, ModelMap m) {
        try {
            if (StringUtils.isNotEmpty(ids)) {
                CodeItem item = itemService.findById(ids);
                m.put("item", item);
                msg.setSuccessMsg("item");
            } else {
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/code/item/edit";
    }

    @RequestMapping("/item/edit")
    public String editItem(CodeItem item, ModelMap m) {
        try {
            itemService.edit(item);
            msg.setSuccessMsg("操作成功！");
        } catch (Exception e) {
            msg.setSuccessMsg("操作失败！");
            e.printStackTrace();
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }


    @RequestMapping("/kind/remove")
    @ResponseBody
    public Msg removeKind(String[] ids, ModelMap m) {
        try {
            if (ArrayUtils.isNotEmpty(ids)) {
                kindService.remove(ids);
                msg.setSuccessMsg("删除成功");
            } else {
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }

    @RequestMapping("/item/remove")
    @ResponseBody
    public Msg removeItem(String[] ids, ModelMap m) {
        try {
            if (ArrayUtils.isNotEmpty(ids)) {
                itemService.remove(ids);
                msg.setSuccessMsg("删除成功");
            } else {
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }

}
