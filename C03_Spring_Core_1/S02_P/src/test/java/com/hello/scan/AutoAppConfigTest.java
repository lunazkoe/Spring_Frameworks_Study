package com.hello.scan;

import com.hello.AutoAppConfig;
import com.hello.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("MemberServiceImpl / OrderServiceImpl의 @Component(\"service\") 적용 후 테스트 실행")
    void duplicateBeanByName() {
        assertThatThrownBy(() -> new AnnotationConfigApplicationContext(AutoAppConfig.class))
                .isInstanceOf(BeanDefinitionStoreException.class);
    }
}