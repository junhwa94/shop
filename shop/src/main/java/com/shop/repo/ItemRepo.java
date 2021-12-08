package com.shop.repo;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// 엔티티 타입 클래스 , 두번째는 기본키 타입
public interface ItemRepo extends JpaRepository<Item, Long> {
}
