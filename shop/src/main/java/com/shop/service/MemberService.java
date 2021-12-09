package com.shop.service;

import com.shop.entity.Member;
import com.shop.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepo memberRepo;

    public Member saveMember(Member member){

        validateDuplicateMember(member);

        return memberRepo.save(member);

    }

    private void validateDuplicateMember(Member member){

        Member findMember = memberRepo.findByEmail(member.getEmail());

        if(findMember != null){

            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

    }
     // UserDetailsService 의 loadUserByUsername메서드 오버라이딩
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        Member member = memberRepo.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }



}
