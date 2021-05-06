package com.madao.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

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
