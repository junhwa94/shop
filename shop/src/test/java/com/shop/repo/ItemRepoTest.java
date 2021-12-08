package com.shop.repo;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")  // 테스트코드 실행 시 H2데이터베이스 사용
class ItemRepoTest {

    @Autowired
    ItemRepo itemRepo;

    @Test
    @DisplayName("상품 저장 테스트") // 테스트할 메소드 위에 선언, 해당 메소드를 테스트 대상으로 지정
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        //itemRepo save 함수 이용해서 db테이블 insert -> Spring Data JPA Dynamic Proxy 이용해서 객체를 동적으로 생성
        Item saveItem = itemRepo.save(item);

        System.out.println(saveItem.toString());

    }

}