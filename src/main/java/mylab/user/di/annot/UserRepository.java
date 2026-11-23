package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 컴포넌트 스캔 시 이 클래스를 Spring Bean으로 자동 등록하기 위해 선언!
@Component
public class UserRepository {

 // Spring이 dbType 필드에 "MySQL" 값을 주입하도록 선언!
 @Value("MySQL")
 private String dbType;

    public UserRepository() {
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public boolean saveUser(String userId, String name) {
        System.out.println(
                "사용자 저장: " + userId + ", " + name
                + " (DB: " + dbType + ")");

        return true;
    }

    @Override
    public String toString() {
        return "UserRepository [dbType=" + dbType + "]";
    }
}