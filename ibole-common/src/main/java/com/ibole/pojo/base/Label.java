package com.ibole.pojo.base;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 标签实体类
 **/

@Getter
@Setter
//@Entity
//@Table(name = "ba_dict")
public class Label extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String labelName;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐

}