package com.madao.model.pojo.article;

import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Entity
@Table(name = "ar_tag", indexes = {@Index(name = "tag_name", columnList = "name")})
public class Tag extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String name;

    /**
     * 英文名称
     */
    @Column(length = 20)
    private String slug;

    @Column(length = 200)
    private String description;

    @Column(length = 50)
    private String icon;

    @Column(length = 30)
    private String color;

    @Column(length = 1)
    private Integer state;

	public String getId() {
		return id;
	}

	public Tag setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Tag setName(String name) {
		this.name = name;
		return this;
	}

	public String getSlug() {
		return slug;
	}

	public Tag setSlug(String slug) {
		this.slug = slug;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Tag setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public Tag setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getColor() {
		return color;
	}

	public Tag setColor(String color) {
		this.color = color;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public Tag setState(Integer state) {
		this.state = state;
		return this;
	}
}