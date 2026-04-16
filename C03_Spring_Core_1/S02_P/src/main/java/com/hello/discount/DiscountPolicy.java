package com.hello.discount;

import com.hello.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
