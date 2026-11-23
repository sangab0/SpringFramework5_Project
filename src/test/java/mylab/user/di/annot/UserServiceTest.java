package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// JUnit5에서 Spring 테스트 기능을 사용하고 Bean을 주입받기 위해 선언
@ExtendWith(SpringExtension.class)
// 테스트에 사용할 Spring XML 설정 파일의 위치 지정
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    // Spring이 관리하는 UserService Bean을 자동 주입받기 위해 선언
    @Autowired
    UserService userService;

    @Test
    void userServiceBean() {
        // UserService Bean이 주입되었는지 검증
        assertNotNull(userService);

        // UserRepository Bean이 주입되었는지 검증
        assertNotNull(userService.getUserRepository());

        // @Value("MySQL")로 주입한 값 검증
        assertEquals("MySQL", userService.getUserRepository().getDbType());

        // SecurityService Bean이 주입되었는지 검증
        assertNotNull(userService.getSecurityService());
    }

    // 비밀번호가 있을 때 사용자 등록이 성공하는지 검증하는 테스트
    @Test
    void registerUserSuccess() {
        assertTrue(
                userService.registerUser("U001", "배상아", "1234"));
    }

    // 비밀번호가 null일 때 사용자 등록이 실패하는지 검증하는 테스트
    @Test
    void registerUserFailWithNullPassword() {
        assertFalse(
                userService.registerUser("U002", "홍길동", null));
    }

    // 비밀번호가 빈 문자열일 때 사용자 등록이 실패하는지 검증하는 테스트
    @Test
    void registerUserFailWithEmptyPassword() {
        assertFalse(
                userService.registerUser("U003", "김영희", ""));
    }
}