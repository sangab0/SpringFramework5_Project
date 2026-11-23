package mylab.user.di.annot;

import org.springframework.stereotype.Component;

//인증 기능을 제공하는 객체를 Spring Bean으로 등록하여
//UserService에서 주입받아 사용할 수 있도록 선언
@Component
public class SecurityService {
    
    public boolean authenticate(String userId, String password) {
        System.out.println("인증: " + userId);
        // 비밀번호가 null이 아니고 빈 문자열이 아니면 인증 성공	
        return password != null && !password.isEmpty();
    }
    
    public boolean authorize(String userId, String resource) {
        System.out.println("권한 부여: " + userId + " for " + resource);
        return true;
    }
}
