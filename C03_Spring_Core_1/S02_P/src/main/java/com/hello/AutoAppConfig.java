package com.hello;

import com.hello.member.MemberRepository;
import com.hello.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
//        basePackages = "com.hello", // Default: 해당 클래스의 패키지 및 하위 패키지
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean("memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
    // 이렇게 직접 설정 파일을 작성해서 직접 스프링 컨테이너를 만들면 수동 빈 등록이 우선권을 가진다
    // - 구체적인 것이 더 중요하다는 대원칙!
    // - 스프링 부트 기본 설정은 수동 / 자동 등록 빈 중복도 안됨!
    //      - spring.main.allow-bean-definition-overriding=true로 변경하면 됨
}
