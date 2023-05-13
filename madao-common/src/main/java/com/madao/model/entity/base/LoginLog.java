package com.madao.model.entity.base;

import com.madao.model.BasePojo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@Entity
@Table(name = "ba_login_log",
		indexes = {
				@Index(name = "login_log_client_ip", columnList = "clientIp"),
				@Index(name = "login_log_user_id", columnList = "userId"),
				@Index(name = "login_log_create_at", columnList = "createAt")
		})
public class LoginLog extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String userId;

	@Column(length = 20)
	private String userName;

	@Column(length = 20)
	private String clientIp;

	@Column(length = 50)
	private String browser;

	@Column(length = 100)
	private String osInfo;

}