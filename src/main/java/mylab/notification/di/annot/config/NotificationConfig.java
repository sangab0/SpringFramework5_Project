package mylab.notification.di.annot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

// XML 대신 이 클래스에서 Spring Bean을 설정하기 위해 선언
@Configuration
public class NotificationConfig {

    // 메서드가 반환하는 이메일 서비스 객체를 Spring Bean으로 등록
    @Bean
    public EmailNotificationService emailNotificationService() {
        // 생성자를 통해 SMTP 서버 주소와 포트 번호 전달
        return new EmailNotificationService("smtp.gmail.com", 587);
    }

    // 메서드가 반환하는 SMS 서비스 객체를 Spring Bean으로 등록
    @Bean
    public SmsNotificationService smsNotificationService() {
        // 생성자를 통해 SMS 제공업체 전달
        return new SmsNotificationService("SKT");
    }

    // 두 알림 서비스를 관리하는 객체를 Spring Bean으로 등록
    @Bean
    public NotificationManager notificationManager() {
        // 위에서 정의한 이메일·SMS Bean을 생성자로 주입
        return new NotificationManager(
                emailNotificationService(),
                smsNotificationService());
    }
}