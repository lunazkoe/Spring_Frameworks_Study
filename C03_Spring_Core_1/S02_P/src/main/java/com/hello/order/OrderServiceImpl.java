package com.hello.order;

import com.hello.discount.DiscountPolicy;
import com.hello.discount.FixDiscountPolicy;
import com.hello.discount.RateDiscountPolicy;
import com.hello.member.Member;
import com.hello.member.MemberRepository;
import com.hello.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 조회
        Member findMember = memberRepository.findById(memberId);

        // 2. 할인 결정
        int discount = discountPolicy.discount(findMember, itemPrice);

        // 3. 주문 생성
        return new Order(memberId, itemName, itemPrice, discount);
    }

    public MemberRepository memberRepository() {
        return memberRepository;
    }
}
