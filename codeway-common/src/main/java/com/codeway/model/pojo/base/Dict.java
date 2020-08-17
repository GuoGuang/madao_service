package com.codeway.model.pojo.base;

import com.codeway.model.BasePojo;
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
@Table(name = "ba_dict",
		indexes = {
				@Index(name = "dict_code", columnList = "code"),
				@Index(name = "dict_parent_id", columnList = "parentId"),
				@Index(name = "dict_create_at", columnList = "createAt")
		})
public class Dict extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @Column(length = 20)
    private String parentId;

    @Column(length = 10)
    private String code;

    @Column(length = 10)
    private String name;

    @Column(length = 200)
    private String description;

	@Column(length = 10)
	private String type;

	private static final long serialVersionUID = 1L;
}