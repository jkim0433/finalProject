package com.example.rosario.dto;

import com.example.rosario.entity.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class SubscriptionDto {

    private Long subscribeId;       // 구독 ID
    private Long ordersId;          // 주문 ID (FK)

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date subscribeStDt;     // 구독 시작일 (배송 요청 날짜)
    private Long totalNum;          // 주문 총 이용 횟수(FK)
    private Long customerId;        // 고객 ID (FK)
    private Long sellerId;          // 판매자 ID (FK)
    private Long deliveryCount;     // 배송 횟수 (FK)
    private Long remainCount;       // 남은 구독 횟수
    private String customerNm;    // 고객 이름 (6월30일추가), 도경변경
    private String customerCno; // 고객 전화번호, 도경변경

    // Subscription 엔티티를 SubscriptionDto로 변환하는 정적 메서드
    public static SubscriptionDto convertToDto(Subscription subscription,String customerNm, String customerCno) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setSubscribeId(subscription.getSubscribeId());
        dto.setOrdersId(subscription.getOrders().getOrdersId());
        dto.setSubscribeStDt(subscription.getSubscribeStDt());
        dto.setTotalNum(subscription.getOrders().getTotalNum());
        dto.setCustomerId(subscription.getCustomer().getCustomerId());
        dto.setSellerId(subscription.getSeller().getSellerId());
        dto.setDeliveryCount(subscription.getDelivery().getDeliveryCount()); //도경추가 delivery테이블조인으로 deliver_count는 delivery에서 불러오기
        dto.setRemainCount(subscription.getRemainCount());
        dto.setCustomerNm(customerNm);
        dto.setCustomerCno(customerCno);
        return dto;
    }

    //도경추가 모든 Subscription 내용불러오기("/api/subscribes")
    public SubscriptionDto(Long subscribeId, Long ordersId, Date subscribeStDt, Long totalNum,
                           Long customerId, Long sellerId, Long deliveryCount, Long remainCount){
        this.subscribeId = subscribeId;
        this.ordersId = ordersId;
        this.subscribeStDt = subscribeStDt;
        this.totalNum = totalNum;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.deliveryCount = deliveryCount;
        this.remainCount = remainCount;
    }
}
