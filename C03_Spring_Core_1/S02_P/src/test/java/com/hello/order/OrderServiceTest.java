package com.hello.order;

import com.hello.AppConfig;
import com.hello.member.Grade;
import com.hello.member.Member;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(member.getId(), "itemA", 20000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}