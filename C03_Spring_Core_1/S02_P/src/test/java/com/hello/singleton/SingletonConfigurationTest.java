package com.hello.singleton;

import com.hello.AppConfig;
import com.hello.member.MemberRepository;
import com.hello.member.MemberServiceImpl;
import com.hello.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonConfigurationTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void configurationTest() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.memberRepository();
        MemberRepository memberRepository2 = orderService.memberRepository();

        assertThat(memberRepository1).isSameAs(memberRepository2);
        assertThat(memberRepository).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
        // 출력: bean = com.hello.AppConfig$$SpringCGLIB$$0@36ac8a63
        // - CGLIB라는 바이트코드 조작 라이브러리
        // - AppConfig를 상속받은 스프링이 관리하는 새로운 AppConfig클래스를 만들고 스프링 빈으로 등록
        // - 빈이 없으면 빈 생성 / 있으면 컨테이너에서 찾아서 반환을 할 것으로 예상
        // - 자식이므로 AppConfig.class로 조회가 가능한 것!
    }
}
