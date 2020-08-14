package com.codeway.model.pojo.user;

import com.codeway.enums.RegisteredType;
import com.codeway.model.BasePojo;
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
@Table(name = "us_user",
		indexes = {
				@Index(name = "user_user_name", columnList = "userName"),
				@Index(name = "user_account", columnList = "account"),
				@Index(name = "user_email", columnList = "email"),
				@Index(name = "user_status", columnList = "status"),
				@Index(name = "user_phone", columnList = "phone"),
				@Index(name = "user_create_at", columnList = "createAt")
		})
public class User extends BasePojo implements Serializable {

	@JoinTable(name = "us_user_role",
			joinColumns = @JoinColumn(name = "user_id",referencedColumnName="id",foreignKey=@ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT) ),
			inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="id",foreignKey=@ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT)))
	@ManyToMany
	@org.hibernate.annotations.ForeignKey(name = "none")
	private Set<Role> roles = new HashSet<>();

	@Transient
	private String captcha;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
	@Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
	private String id;

	@Column(length = 20)
	private String account;

	@Column(length = 40)
	private String userName;

	@Column(length = 80)
	private String nickName;

	@Column(length = 100)
	private String password;

	@Column(length = 1)
	private int sex;

	@Column(length = 13)
	private Long birthday;

	@Column(length = 300)
	private String avatar;

	@Column(length = 30)
	private String email;

	@Column(length = 13)
	private Long lastDate;

	@Column(length = 13)
	private Long onlineTime;

	/**
	 * 兴趣
	 */
	@Column(length = 30)
	private String interest;

	/**
	 * 个性
	 */
	@Column(length = 20)
	private String personality;

	@Column(length = 10)
	private Integer fansCount;

	@Column(length = 10)
	private Integer followCount;

	@Column(length = 15)
	private String phone;

	@Column(length = 200)
	private String contactAddress;

	@Column(length = 10)
	private RegisteredType registeredType;

	/**
	 * 是否锁定
	 */
	@Column(length = 1)
	private Boolean status;

}