package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// JUnit5와 Spring Test를 연결
@ExtendWith(SpringExtension.class)
// 테스트에서 사용할 XML 설정 파일 지정
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    // XML에 등록된 ShoppingCart Bean 주입
    @Autowired
    ShoppingCart shoppingCart;

    // XML에 등록된 OrderService Bean 주입
    @Autowired
    OrderService orderService;

    @Test
    void shoppingCartBean() {
        // 1. shoppingCart 객체가 null이 아닌지 검증
        assertNotNull(shoppingCart);

        // 2. 장바구니에 등록된 상품이 2개인지 검증
        assertEquals(2, shoppingCart.getProducts().size());

        // 3. 첫 번째 상품명이 "노트북"인지 검증
        assertEquals("노트북",
                shoppingCart.getProducts().get(0).getName());

        // 4. 두 번째 상품명이 "스마트폰"인지 검증
        assertEquals("스마트폰",
                shoppingCart.getProducts().get(1).getName());
    }

    @Test
    void orderServiceBean() {
        // 1. orderService 객체가 null이 아닌지 검증
        assertNotNull(orderService);

        // 2. OrderService에 ShoppingCart가 주입되었는지 검증
        assertNotNull(orderService.getShoppingCart());

        // 3. 주문 합계 검증: 150000 + 800000 = 950000
        assertEquals(950000.0,
                orderService.calculateOrderTotal(), 0.001);
    }
}