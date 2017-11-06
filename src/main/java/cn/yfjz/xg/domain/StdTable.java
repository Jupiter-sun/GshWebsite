package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import com.avaje.ebean.annotation.DbComment;

import javax.persistence.*;

/**
 * Created by liwj on 16/8/16.
 */
@Entity
@Table(name = "std_table")
@DbComment("学生信息子集表")
public class StdTable extends BaseModel {
	//表ID
    @Column(length = 100)
    private String tableId;
    //表名
    @Column(length = 100)
    private String tableName;
    @ManyToOne @JoinColumn(name = "type_id")
    private StdTableType type;
    //表名（中文）
    @Column(length = 100)
    private String tableDesc;
    @Column(length = 5)
    private Long priority;
    @Column(length = 10)
    private String relation;
    @Column(length = 255)
    private String remark;
    @Column(length = 2)
    private String isUse;
    @Column(length = 2)
    private String isAudit;
    @Column(length = 2)
    private String isUpload;
    @Column(length = 2)
    private String isEdit;
    @Column(length = 100)
    private String vwName;
    @Column(length = 100)
    private String tempName;
    @Column
	private Long orderno;
    
    

    public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public StdTableType getType() {
        return type;
    }

    public void setType(StdTableType type) {
        this.type = type;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getVwName() {
        return vwName;
    }

    public void setVwName(String vwName) {
        this.vwName = vwName;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

	public Long getOrderno() {
		return orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}
    
}
