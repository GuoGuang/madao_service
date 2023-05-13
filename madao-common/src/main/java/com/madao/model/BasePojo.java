package com.madao.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePojo implements Serializable {

	@LastModifiedDate
	@Column(length = 13)
	private Long updateAt;

	@CreatedDate
	@Column(length = 13)
	private Long createAt;

	public Long getUpdateAt() {
		return updateAt;
	}

	public BasePojo setUpdateAt(Long updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	public Long getCreateAt() {
		return createAt;
	}

	public BasePojo setCreateAt(Long createAt) {
		this.createAt = createAt;
		return this;
	}
}
