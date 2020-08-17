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
@Table(name = "ba_login_log",
		indexes = {
				@Index(name = "login_log_client_ip", columnList = "clientIp"),
				@Index(name = "login_log_user_id", columnList = "userId"),
				@Index(name = "login_log_create_at", columnList = "createAt")
		})
public class LoginLog extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
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