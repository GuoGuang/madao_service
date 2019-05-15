package com.youyd.pojo.base;

import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 标签实体类
 * @author: LGG
 * @create: 2018-09-26 15:55
 **/

@Getter
@Setter
public class Label extends BasePojo  implements Serializable {

	private String id;
	private String labelName;//标签名称
	private String state;//状态
	private Long count;//使用数量
	private Long fans;//关注数
	private String recommend;//是否推荐

}