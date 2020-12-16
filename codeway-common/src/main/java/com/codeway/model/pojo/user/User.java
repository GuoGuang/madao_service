package com.madaoo.model.pojo.user;

import com.madaoo.enums.ProviderEnum;
import com.madaoo.model.BasePojo;
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

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madaoo.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
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
	private Boolean sex;

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

	/**
	 * 是否锁定
	 */
	@Column(length = 1)
	private Boolean status;

	@Column(length = 1)
	private Boolean origin;

	@Column(length = 20)
	private String bindId;

	@Column(length = 1)
	private ProviderEnum provider;
}