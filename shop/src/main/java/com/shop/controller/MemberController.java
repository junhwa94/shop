package com.shop.controller;

import com.shop.dto.MemberFormDTO;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 페이지
    @GetMapping(value = "/new")
    public String memberForm(Model model){

        model.addAttribute("memberFormDTO", new MemberFormDTO());

        return "member/memberForm";
    }

    // 회원가입 폼 처리 과정
    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model){

        try {
            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    // 로그인
    @GetMapping(value = "/login")
    public String loginMember(){

        return "/member/memberLoginForm";
    }

    // 로그인 실패 시
    @GetMapping(value = "/login/error")
    public String loginError(Model model){

        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");

        return "/member/memberLoginForm";
    }

}
