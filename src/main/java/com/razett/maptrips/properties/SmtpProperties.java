package com.razett.maptrips.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SMTP를 이용한 Mail 전송에 필요한 값을 application.properties에서 불러옵니다.
 *
 * @since 2024-10-18 v1.0.0
 * @author JiwonJeong
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "maptrips.mail.smtp")
@Data
public class SmtpProperties {
    private String host;
    private String port;
    private String auth;
    private String startTls;
    private String fromEmail;
    private String password;
}
