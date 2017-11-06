package cn.yfjz.xg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.CodeKind;

@Entity
@Table(name = "std_field")
public class StdField extends BaseModel {
	
	@ManyToOne @JoinColumn(name = "table_id")
	private StdTable table;
	
	@Column(length = 30)
	private String fieldId;
	
	@Column(length = 100)
	private String fieldDesc;
	
	@Column(length = 100)
	private String fieldGroup;
	
	@Column(length = 2)
	private String isEdit;
	
	@Column(length = 2)
	private String isManage;
	
	@Column(length = 2)
	private String isRequired;
	
	@Column(length = 2)
	private String isAudit;
	
	@Column
	private Long orderno;
	
	@Column(length = 255)
    private String remark;
	
	@Column(length = 2)
	private String isValid;
	
	@Column(length = 2)
	private String fieldType;
	
	@ManyToOne @JoinColumn(name = "kind_id")
	private CodeKind kind;
	
	@Column(length = 2)
	private String isApply;
	
	@Column(length = 255)
    private String selectValue;
	
	@Column(length = 255)
    private String defaultValue;
	
	@Column(length = 2)
    private String isQuery;
	
	@Column(length = 20)
    private String pattern;
	
	@Column(length = 255)
	private String fieldTitle;
	
	public StdTable getTable() {
		return table;
	}

	public void setTable(StdTable table) {
		this.table = table;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroup(String fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getIsManage() {
		return isManage;
	}

	public void setIsManage(String isManage) {
		this.isManage = isManage;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public Long getOrderno() {
		return orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public CodeKind getKind() {
		return kind;
	}

	public void setKind(CodeKind kind) {
		this.kind = kind;
	}

	public String getIsApply() {
		return isApply;
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

}
