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
@Table(name = "p_import_checkrule")
public class ImportCheckRule extends BaseModel {
    @ManyToOne
    private ImportModel imp;
    @Column(length = 100)
    private String ruleName;
    @Column(length = 1000)
    private String checkRule;

    public ImportModel getImp() {
        return imp;
    }

    public void setImp(ImportModel imp) {
        this.imp = imp;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }
}
