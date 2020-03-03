package com.codeif.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基础实体类,其他实体应继承此实体
 **/
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePojo implements Serializable {

	@LastModifiedDate
	private Long updateAt;

	@CreatedDate
	private Long createAt;


}
