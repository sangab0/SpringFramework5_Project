package myspring.di.xml;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class HelloBeanSpringTest {

	@Autowired
	Hello hello; // Hello helloByType =  factory.getBean("hello", Hello.class); 이 문장과 같음 
	
	// @Autowired - Bean의 Class Type으로 의존하는 객체를 찾는 방식 
	@Autowired
	@Qualifier("helloCons")
	Hello hello2;
	
	// @Resource - Bean의 name(id) 값으로 의존하는 객체를 찾는 방식 
	@Resource(name = "helloCons")
	Hello hello3;
	
	@Test @Disabled // 이 메서드 비활성화
	void helloBean() {
		assertEquals("Hello 스프링", hello.sayHello());
		
		assertEquals("Hello 생성자", hello2.sayHello());
		
		assertEquals("Hello 생성자", hello3.sayHello());
		
		hello2.print();
		
		hello2.print();
		

	}
	
	@Test
	void list() {
		
	}
	
	
}

