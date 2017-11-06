package cn.yfjz.core.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "p_code_item")
public class CodeItem extends BaseModel {
	@Column(length = 200, nullable = false)
	private String itemName;
	@Column(length = 2)
	private String status;
	@Column(length = 5)
	private Long orderNo;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="kind_id")
	private CodeKind codekind;
	@Column(length = 200)
	private String tranCode;
	@Column(length = 32)
	private String parentId;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public CodeKind getCodekind() {
		return codekind;
	}

	public void setCodekind(CodeKind codekind) {
		this.codekind = codekind;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
