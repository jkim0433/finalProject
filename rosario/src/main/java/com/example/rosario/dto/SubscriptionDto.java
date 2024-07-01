package com.example.rosario.dto;

import com.example.rosario.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {

    private Long subscribeId;       // 구독 ID
    private Long ordersId;          // 주문 ID (FK)
    private Date subscribeStDt;     // 구독 시작일 (배송 요청 날짜)
    private Long totalNum;          // 주문 총 이용 횟수
    private Long customerId;        // 고객 ID (FK)
    private Long sellerId;          // 판매자 ID (FK)
    private Long deliveryCount;     // 배송 횟수
    private Long remainCount;       // 남은 구독 횟수
    private String customerName;    // 고객 이름 (6월30일추가)
    private String customerPhoneNumber; // 고객 전화번호

    // Subscription 엔티티를 SubscriptionDto로 변환하는 정적 메서드
    public static SubscriptionDto convertToDto(Subscription subscription,String customerNm, String customerCno) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setSubscribeId(subscription.getSubscribeId());
        dto.setOrdersId(subscription.getOrders().getOrdersId());
        dto.setSubscribeStDt(subscription.getSubscribeStDt());
        dto.setTotalNum(subscription.getTotalNum());
        dto.setCustomerId(subscription.getCustomer().getCustomerId());
        dto.setSellerId(subscription.getSeller().getSellerId());
        dto.setDeliveryCount(subscription.getDeliveryCount());
        dto.setRemainCount(subscription.getRemainCount());
        dto.setCustomerName(customerNm);
        dto.setCustomerPhoneNumber(customerCno);
        return dto;
    }
}
