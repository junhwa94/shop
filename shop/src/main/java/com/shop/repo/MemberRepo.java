package com.shop.repo;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {

    // 회원 가입 시 중복된 회원이 있는지 검사하기 위한 쿼리 메서드
    Member findByEmail(String email);
}
