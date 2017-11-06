package cn.yfjz.core.sys.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;

@Entity
@Table(name = "p_code_kind")
public class CodeKind extends BaseModel{
	@Column(length = 200, nullable = false)
	private String kindName;
	@Column(length = 2)
	private String status;
	@Column(length = 5)
	private Long displayWidth;
	@Column(length = 100)
	private String codeGroup;
	@WhenCreated
	@Column(columnDefinition = "DATE")
	Timestamp whenCreated;
	@WhenModified
	@Column(columnDefinition = "DATE")
	Timestamp whenModified;

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayWidth(Long displayWidth) {
		this.displayWidth = displayWidth;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public Timestamp getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(Timestamp whenCreated) {
		this.whenCreated = whenCreated;
	}

	public Timestamp getWhenModified() {
		return whenModified;
	}

	public void setWhenModified(Timestamp whenModified) {
		this.whenModified = whenModified;
	}
}
