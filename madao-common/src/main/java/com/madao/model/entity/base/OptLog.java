package com.madao.model.entity.base;

import com.madao.enums.OptLogType;
import com.madao.model.BasePojo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@Entity
@Table(name = "ba_opt_log",
		indexes = {
				@Index(name = "opt_log_client_ip", columnList = "clientIp"),
				@Index(name = "opt_log_user_id", columnList = "userId"),
				@Index(name = "opt_log_create_at", columnList = "createAt")
		})
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OptLog extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(columnDefinition = "varchar(20) COMMENT '用户id'", nullable = false)
	private String userId;

	@Transient
	private String userName;

	@Column(columnDefinition = "varchar(20) COMMENT '客户端IP'", nullable = false)
	private String clientIp;


	@Column(columnDefinition = "int COMMENT '操作类型'", nullable = false)
	private OptLogType type;

	@Column(columnDefinition = "varchar(100) COMMENT '方法'", nullable = false)
	private String method;

	@Column(columnDefinition = "varchar(500) COMMENT '参数'")
	private String params;

	@Column(columnDefinition = "varchar(500) COMMENT '异常信息'")
	private String exceptionDetail;

	@Column(columnDefinition = "varchar(50) COMMENT '浏览器'")
	private String browser;

	@Column(columnDefinition = "varchar(50) COMMENT '系统信息'")
	private String osInfo;
}