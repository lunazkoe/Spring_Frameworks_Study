package com.hello;

import com.hello.discount.DiscountPolicy;
import com.hello.discount.FixDiscountPolicy;
import com.hello.discount.RateDiscountPolicy;
import com.hello.member.MemberRepository;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import com.hello.member.MemoryMemberRepository;
import com.hello.order.OrderService;
import com.hello.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {

    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(
                memberRepository()
        );
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(), discountPolicy()
//                memberRepository, discountPolicy // 필드 주입으로 자동 주입 가능
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
