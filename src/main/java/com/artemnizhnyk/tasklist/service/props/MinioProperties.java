package com.artemnizhnyk.tasklist.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "minio")
@Component
public class MinioProperties {

    private String bucket;
    private String url;
    private String accessKey;
    private String secretKey;
}
