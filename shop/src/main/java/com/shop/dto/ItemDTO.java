package com.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

// 뷰 영역에서 사용하기 위해 dto클래스 생성, 데이터를 주고 받을 때
// entity 클래스 자체를 반환해서는 안되고 데이터 전달용 객체 dto를 생성해서 사용해야 함.
@Data
public class ItemDTO {

    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
