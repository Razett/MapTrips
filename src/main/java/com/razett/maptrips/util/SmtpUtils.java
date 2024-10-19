package com.razett.maptrips.util;

import com.razett.maptrips.properties.SmtpProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * SMTP를 이용하여 Email을 발송하는 유틸
 *
 * @since 2024-10-19 v1.0.0
 * @author jeongjiwon
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class SmtpUtils {

    private final SmtpProperties smtpProperties;

    /**
     * SMTP를 이용하여 Email을 발송합니다.
     * @param toEmail 수신자 Email 주소
     * @param subject 메일 제목
     * @param body 메일 본문
     * @return 메일 발송 시 true, 실패 시 false
     *
     * @since 2024-10-19 v1.0.0
     * @author jeongjiwon
     */
    public boolean sendEmail(String toEmail, String subject, String body) {
        // Gmail SMTP 서버 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpProperties.getHost()); // SMTP 서버 설정
        properties.put("mail.smtp.port", smtpProperties.getPort()); // 포트 번호
        properties.put("mail.smtp.auth", smtpProperties.getAuth()); // 인증 여부
        properties.put("mail.smtp.starttls.enable", smtpProperties.getStartTls()); // TLS 사용 여부

        // 인증 정보 설정
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpProperties.getFromEmail(), smtpProperties.getPassword());
            }
        };

        // 세션 생성
        Session session = Session.getInstance(properties, auth);

        try {
            // 메시지 구성
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(smtpProperties.getFromEmail()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(body);

            // 이메일 전송
            Transport.send(msg);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
