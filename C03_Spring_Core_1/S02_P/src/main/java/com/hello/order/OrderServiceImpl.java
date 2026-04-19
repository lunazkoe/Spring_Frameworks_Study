package com.hello.order;

import com.hello.annotation.MainDiscountPolicy;
import com.hello.discount.DiscountPolicy;
import com.hello.discount.FixDiscountPolicy;
import com.hello.discount.RateDiscountPolicy;
import com.hello.member.Member;
import com.hello.member.MemberRepository;
import com.hello.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("service")
@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // 1. 생성자 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired // 생성자가 1개면 생략해도 자동 주입 (스프링 빈만 해당)
    // 롬북 사용시 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository,
//                            DiscountPolicy discountPolicy
//                            DiscountPolicy rateDiscountPolicy
//                            @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
                            @MainDiscountPolicy DiscountPolicy discountPolicy
    ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
//        this.discountPolicy = rateDiscountPolicy; // 빈 조회 2개 이상일 시 특정 이름으로 하면 이름으로 매핑 시도
    }

    // 2. Setter 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//
////    @Autowired(required = false) // 자동 주입할 대상이 없어도 동작(기본값 true)
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 3. 필드 주입
    // - 주의: 순수한 자바 테스트 코드에서는 동작하지 않음
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 4. 일반 메서드 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 조회
        Member findMember = memberRepository.findById(memberId);

        // 2. 할인 결정
        int discount = discountPolicy.discount(findMember, itemPrice);

        // 3. 주문 생성
        return new Order(memberId, itemName, itemPrice, discount);
    }

    // 테스트 용도
    public MemberRepository memberRepository() {
        return memberRepository;
    }
}
