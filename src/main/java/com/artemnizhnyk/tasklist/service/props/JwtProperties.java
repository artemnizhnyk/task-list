package com.artemnizhnyk.tasklist.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "security.jwt")
@Component
public class JwtProperties {

    private String secret;
    private Long access;
    private Long refresh;
}
