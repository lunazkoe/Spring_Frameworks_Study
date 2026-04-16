package com.hello;

import com.hello.member.Grade;
import com.hello.member.Member;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member newMember = new Member(1L, "memberA", Grade.VIP);
        memberService.join(newMember);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + newMember.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
