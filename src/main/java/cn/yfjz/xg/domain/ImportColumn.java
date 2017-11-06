package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/11/21.
 */
@Entity
@Table(name = "p_import_column")
public class ImportColumn extends BaseModel {
    @ManyToOne
    private ImportModel imp;
    @Column(length = 100)
    private String columnName;
    @Column(length = 100)
    private String showName;
    @Column(length = 2)
    private String isColumn;
    @Column(length = 2)
    private String isQuery;

    public ImportModel getImp() {
        return imp;
    }

    public void setImp(ImportModel imp) {
        this.imp = imp;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getIsColumn() {
        return isColumn;
    }

    public void setIsColumn(String isColumn) {
        this.isColumn = isColumn;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }
}
