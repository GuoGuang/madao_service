package com.madao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePojo implements Serializable {

	@LastModifiedDate
	@Column(length = 13)
	private Long updateAt;

	@CreatedDate
	@Column(length = 13)
	private Long createAt;


}
