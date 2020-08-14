package com.codeway.model.pojo.user;

import com.codeway.model.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_role",
		indexes = {
				@Index(name = "role_code", columnList = "code"),
				@Index(name = "role_create_at", columnList = "createAt")
		})
public class Role extends BasePojo implements Serializable {

	@ManyToMany
	@org.hibernate.annotations.ForeignKey(name = "none")
	@JoinTable(name = "us_role_resource",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)))
	private Set<Resource> resources = new HashSet<>();

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "none")
	private Set<User> users = new HashSet<>();


    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    @Column(length = 20)
    private String roleName;

	@Column(length = 200)
	private String roleDesc;

	@Column(length = 20)
	private String code;

}