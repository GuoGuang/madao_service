package com.ibole.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础实体类,其他实体应继承此实体
 **/
@Getter
@Setter
public class BasePojo implements Serializable {

	private Long updateAt;
	private Long createAt;

}
