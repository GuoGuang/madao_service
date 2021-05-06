package com.madao.model.pojo.base;

import com.madao.model.BasePojo;

import javax.validation.constraints.NotNull;
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
public class Music extends BasePojo implements Serializable {

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

    private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public Music setId(String id) {
		this.id = id;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public Music setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Music setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public Music setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Music setDescription(String description) {
		this.description = description;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public Music setState(Integer state) {
		this.state = state;
		return this;
	}

	public String getType() {
		return type;
	}

	public Music setType(String type) {
		this.type = type;
		return this;
	}
}