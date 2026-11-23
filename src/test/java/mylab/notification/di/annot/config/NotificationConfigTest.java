package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

// JUnit5에서 Spring 테스트 기능과 의존성 주입을 사용하기 위해 선언
@ExtendWith(SpringExtension.class)
// XML 경로 대신 Java 설정 클래스를 지정하여 Spring 컨테이너 로딩
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    // 설정 클래스에 등록한 NotificationManager Bean을 자동 주입
    @Autowired
    NotificationManager notificationManager;

    // NotificationManager가 정상적으로 주입되었는지 검증
    @Test
    void notificationManagerBean() {
        assertNotNull(notificationManager);
    }

    // 이메일 서비스 주입 여부와 설정값 검증
    @Test
    void emailServiceBean() {
        assertNotNull(notificationManager.getEmailService());

        // 반환 타입이 NotificationService이므로 실제 구현 타입 확인
        assertTrue(
                notificationManager.getEmailService()
                        instanceof EmailNotificationService);

        // 이메일 전용 getter를 사용하기 위해 구현 클래스 타입으로 형변환
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();

        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());
    }

    // SMS 서비스 주입 여부와 설정값 검증
    @Test
    void smsServiceBean() {
        assertNotNull(notificationManager.getSmsService());

        assertTrue(
                notificationManager.getSmsService()
                        instanceof SmsNotificationService);

        // SMS 전용 getter를 사용하기 위해 구현 클래스 타입으로 형변환
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();

        assertEquals("SKT", smsService.getProvider());
    }

    // 매니저를 통한 이메일·SMS 알림 호출이 예외 없이 실행되는지 검증
    @Test
    void sendNotifications() {
        assertDoesNotThrow(() -> {
            notificationManager.sendNotificationByEmail("테스트 이메일");
            notificationManager.sendNotificationBySms("테스트 SMS");
        });
    }
}