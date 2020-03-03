package com.codeif.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基础实体类,其他实体应继承此实体
 **/
@Getter
@Setter
@MappedSuperclass
public class BasePojo implements Serializable {

	private Long updateAt;
	private Long createAt;

}
