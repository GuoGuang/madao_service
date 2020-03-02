package com.codeif.pojo.tweets;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 吐槽，推特
 **/
@ApiModel(value = "Tweets", description = "吐槽，推特")
@Getter
@Setter
//@Entity
//@Table(name = "t_hotel", schema = "test", catalog = "")
public class Tweets extends BasePojo implements Serializable {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)

    private String id; // 吐槽表ID

    @NotNull(message="标题不能为空")
    private String title; // 标题

	@NotNull(message="内容不能为空")
    private String content; // 吐槽内容

    private String userId; // 发布人ID

    private String nickName; // 发布人昵称

    private Long visitsCount; // 浏览量

    private Long thumbUpCount; // 点赞数

    private Long shareCount; // 分享数

    private Integer isVisible; // 是否可见
}