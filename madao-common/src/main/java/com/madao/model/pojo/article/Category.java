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
@Table(name = "ar_category",
        indexes = {
                @Index(name = "categories_name", columnList = "name"),
                @Index(name = "categories_parent_id", columnList = "parentId")
        })
public class Category extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String parentId;

    @Column(length = 20)
    private String name;

    @Column(length = 200)
    private String summary;

    @Column(length = 20)
    private String userId;

}