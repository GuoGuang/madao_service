package com.madao.model.entity.base;

import com.madao.model.BasePojo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
//@Entity
//@Table(name = "")
@Getter
@Setter
public class Music extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	//  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
	private String id;
	private String parentId;
	/**
	 * 编码
	 */
	@NotNull(message = "编码不能为空")
	private String code;
	/**
	 * 名称
	 */
	private String name;
	private String description;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 类型
	 */
	private String type;

}