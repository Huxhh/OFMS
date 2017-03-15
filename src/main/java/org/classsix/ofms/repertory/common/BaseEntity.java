package org.classsix.ofms.repertory.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/** 系统公共字段实体类 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -1730442499015839066L;

	/**  是否删除: 0 不删除，1 删除 */
	@Column(name = "ISDELETE", length = 2)
	private int isDelete = 0;

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

}
