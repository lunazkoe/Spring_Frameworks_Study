package com.hello.discount;

import com.hello.annotation.MainDiscountPolicy;
import com.hello.member.Grade;
import com.hello.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("subDiscountPolicy")
//@Primary // 여러 빈 매칭시 우선권을 가짐
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
