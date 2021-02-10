package com.madao.model.pojo.user;

import com.madao.model.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_resource",
        indexes = {
                @Index(name = "resource_type", columnList = "type"),
                @Index(name = "resource_code", columnList = "code"),
                @Index(name = "resource_create_at", columnList = "createAt")
        })
public class Resource extends BasePojo implements Serializable, Cloneable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String parentId;

    @Column(length = 30)
    private String component;

    @Column(length = 50)
    private String icon;

    @Column(length = 100)
    private String path;

    @Column(precision = 30, scale = 1, length = 5)
    private float sort;

    @Column
    private Boolean isHidden;

    @Column(length = 200)
    private String description;

    /**
     * btn 或resource
     */
    @Column(length = 10)
    private String type;

    /**
     * 请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径
     */
    @Column(length = 30)
    private String url;

    @Column(length = 20)
    private String method;

    @Column(length = 20)
    private String code;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}