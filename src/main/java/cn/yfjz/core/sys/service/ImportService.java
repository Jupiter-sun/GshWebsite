package cn.yfjz.core.sys.service;

import cn.yfjz.xg.domain.ImportColumn;
import cn.yfjz.xg.domain.ImportModel;

import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/11/21.
 */
public interface ImportService {
    List<ImportColumn> queryColumns(String impId);
    ImportModel findById(String id);
    List<Map> saveTemp(ImportModel importModel, List<ImportColumn> columns, String pc, List<String> titles, List<List<String>> content, List<String> cList);
    String[] saveData(String importId, String pc, List<String> cList);
}
