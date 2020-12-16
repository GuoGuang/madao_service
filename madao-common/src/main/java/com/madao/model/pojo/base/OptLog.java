package com.madao.model.pojo.base;

import com.madao.enums.OptLogType;
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
@Table(name = "ba_opt_log",
		indexes = {
				@Index(name = "opt_log_client_ip", columnList = "clientIp"),
				@Index(name = "opt_log_user_id", columnList = "userId"),
				@Index(name = "opt_log_create_at", columnList = "createAt")
		})
public class OptLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20)
    private String userId;

    @Transient
    private String userName;

    @Column(length = 20)
    private String clientIp;

	@Column(length = 1)
	private OptLogType type;

    @Column(length = 100)
    private String method;

    @Column(length = 500)
    private String params;

    @Column(length = 500)
    private String exceptionDetail;

    @Column(length = 50)
    private String browser;

    @Column(length = 50)
    private String osInfo;

}