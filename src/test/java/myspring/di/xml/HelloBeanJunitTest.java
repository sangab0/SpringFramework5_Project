package myspring.di.xml;

//Assertions 클래스의 모든 static 메서드를 임포트 한다.
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	BeanFactory factory;
	
	@BeforeEach
	void container() {
		// 1. Bean Container 객체생성 
		// 스프링 컨테이너가 XML 설정 파일을 읽어 객체(Bean)를 생성하고,
		// setter 메서드를 호출하여 값이나 객체를 주입하는 것을 콘솔 출력으로 확인할 수 있음 !
		factory = new GenericXmlApplicationContext("classpath:spring-beans.xml");
	}
	
	@Test
	void hello() {

		// 2. Hello Baen을 Container에게 요청하기 Bean의 id를 기준으로 Bean을 요청하기
		Hello helloById = (Hello)factory.getBean("hello");
		// 2-1 Hello Baen을 Container에게 요청하기 Bean의 id와 Type을 기준으로 Bean을 요청하기
		// 위보다 이걸 더 권장함 
		Hello helloByType =  factory.getBean("hello", Hello.class);
		
		// 2개의 Hello Bean의 주소비교 
		System.out.println(helloById == helloByType);
		// assertSame() 메서드 사용 - 두 변수가 같은 Hello 객체를 가리키는지 검증함
		assertSame(helloById, helloByType);
		
		// setName() Injection을 검증하기 - 값 비교 
		assertEquals("Hello 스프링", helloByType.sayHello());

		// setPrinter() Injection을 검증하기 
		helloByType.print();
		
		// 3. StringPrinter Bean을 요청하기 
		Printer printer = factory.getBean("strPrinter",Printer.class);
		
		assertEquals("Hello 스프링", printer.toString());
	}

}
