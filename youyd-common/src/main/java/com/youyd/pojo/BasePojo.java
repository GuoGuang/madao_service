package com.youyd.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 基础实体类,其他实体应继承此实体
 * @author: LGG
 * @create: 2018-09-27 14:19
 **/
@Data
public class BasePojo extends QueryVO implements Serializable {

	private Date updateAt;
	private Date createAt;

}
