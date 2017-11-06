package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwj on 16/11/21.
 */
@Entity
@Table(name = "p_import")
public class ImportModel extends BaseModel {
    @Column(length = 100)
    private String showName;
    @Column(length = 100)
    private String tableName;
    @Column(length = 100)
    private String exportProcedure;
    @Column(length = 2000)
    private String remark;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getExportProcedure() {
        return exportProcedure;
    }

    public void setExportProcedure(String exportProcedure) {
        this.exportProcedure = exportProcedure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
