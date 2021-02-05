package com.madao.model.pojo.article;

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

}