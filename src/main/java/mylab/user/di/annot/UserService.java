package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 사용자 등록 기능을 제공하는 객체를 Spring Bean으로 자동 등록하기 위해 선언!
@Component
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
    
	@Autowired
    private SecurityService securityService;
        
    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }
    
    public boolean registerUser(String userId, String name, String password) {
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name); // 인증에 성공한 경우 사용자 저장
        }
        return false;
    }
}