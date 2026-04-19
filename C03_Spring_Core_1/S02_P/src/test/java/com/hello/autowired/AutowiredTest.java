package com.hello.autowired;

import com.hello.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        ac.getBean(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false) // 수정자 자체가 호출이 안됨
        public void setNoBean1(Member member) {
            System.out.println("member = " + member);
        }

        @Autowired // null을 넣음
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        @Autowired // Optional.empty를 넣음
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }
    }
}
