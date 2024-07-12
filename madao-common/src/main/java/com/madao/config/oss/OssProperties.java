package com.madao.config.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
