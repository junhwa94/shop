package com.shop.service;

import com.shop.entity.Member;
import com.shop.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

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


}
