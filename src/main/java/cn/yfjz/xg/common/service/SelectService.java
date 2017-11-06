package cn.yfjz.xg.common.service;

import cn.yfjz.core.sys.domain.ClassList;
import cn.yfjz.core.sys.domain.Major;
import cn.yfjz.core.sys.domain.XgCode;
import cn.yfjz.xg.domain.SelectKey;
import cn.yfjz.xg.domain.Xueyuan;

import java.util.List;

/**
 * Created by liwj on 16/9/24.
 */
public interface SelectService {
    List<Xueyuan> queryXueyuan();

    List<Major> queryMajor(String xyIds, String njs);

    List<ClassList> queryClasslist(String xyIds, String njs, String zyIds);

    List<XgCode> queryCommon(SelectKey key, String parentId);

    List<String> queryNj();

    List queryXn();
}
