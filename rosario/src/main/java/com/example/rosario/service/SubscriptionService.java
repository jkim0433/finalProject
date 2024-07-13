package com.example.rosario.service;

import com.example.rosario.dto.OrdersDto;
import com.example.rosario.dto.SubscriptionDto;
import com.example.rosario.dto.DeliveryDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.entity.Subscription;
import com.example.rosario.repository.CustomerRepository;
import com.example.rosario.repository.OrdersRepository;
import com.example.rosario.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 구독자 및 비구독자 퍼센트, 리스트  service
@Service
public class SubscriptionService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //도경등록(0712), 모든구독자조회
    public List<SubscriptionDto> getAllSubscription(){
        List<Subscription> subscriptionList = subscriptionRepository.findAll();
        return subscriptionList.stream().map(subscription -> new SubscriptionDto(
                subscription.getSubscribeId(),
                subscription.getOrders().getOrdersId(),
                subscription.getSubscribeStDt(),
                subscription.getOrders().getTotalNum(),
                subscription.getCustomer().getCustomerId(),
                subscription.getSeller().getSellerId(),
                subscription.getDeliveryCount(),
                subscription.getRemainCount()
        ))
                .collect(Collectors.toList());
    }




    // 월별 구독 퍼센트 계산 메서드
    public double calculateSubscriptionPercentage(int year, int month) {
        // Double 타입으로 반환되는 메서드 호출
        // Repository를 통해 데이터베이스 쿼리 실행
        Double subscribedPercentage = ordersRepository.calculateSubscriptionPercentage(year, month);

        // Null 체크 후 double 타입으로 변환하여 반환
        // 결과가 null이 아니면 double로 변환하여 반환, null이면 0.0 반환
        return subscribedPercentage != null ? subscribedPercentage : 0.0;

    }

    // 월별 구독자 리스트
    public List<OrdersDto> getSubscribedList(int year, int month) {
        return ordersRepository.findSubscribedList(year, month);
    }

    // 월별 비구독자 리스트
    public List<OrdersDto> getUnsubscribedList(int year, int month) {
        return ordersRepository.findUnsubscribedList(year, month);
    }

    // 구독자 리스트 => Subscription 엔티티 기반으로 한 구독 정보 조회 및 변화
    // 구독자 리스트 조회 및 고객 이름과 연락처 추가
    public List<SubscriptionDto> getSubscriptionList() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()  // 스트림으로 변환하여 처리 시작(순차적으로 처리하고 변환할 수 있음)
                .map(subscription -> customerRepository.findById(subscription.getCustomer().getCustomerId()) // 현재 구독('subscription')의 고객id가져옴
                        .map(customer -> SubscriptionDto.convertToDto(subscription, customer.getCustomerNm(), customer.getCustomerCno()))
                        .orElse(null))
                .filter(dto -> dto != null) //dto객체가 null이 아닌 경우에만 허용
                .collect(Collectors.toList()); // 스트림에서 요소들을 'List'형태로 모아서 반환
    }

    // SellerId를 기준으로 구독자 리스트 조회
    public List<SubscriptionDto> getSubscriptionListBySellerId(Long sellerId) {
        List<Subscription> subscriptions = subscriptionRepository.findBySeller_SellerId(sellerId);
        return subscriptions.stream()
                .map(subscription -> customerRepository.findById(subscription.getCustomer().getCustomerId())
                        .map(customer -> SubscriptionDto.convertToDto(subscription, customer.getCustomerNm(), customer.getCustomerCno()))
                        .orElse(null))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
    //    // SellerId를 기준으로 구독자 리스트 조회
    //    public List<SubscriptionDto> getSubscriptionListBySellerId(Long sellerId) {
    //        List<Subscription> subscriptions = subscriptionRepository.findBySellerId(sellerId);
    //        return subscriptions.stream()
    //                .map(subscription -> SubscriptionDto.convertToDto(subscription))
    //                .collect(Collectors.toList());
    //    }
}

//    @Autowired
//    private SubscriptionRepository subscriptionRepository;
//
//    public double calculateSubscriptionPercentage(int year, int month) {
//        Long totalCustomers = subscriptionRepository.countSubscribedCustomers(year, month) + subscriptionRepository.countUnsubscribedCustomers(year, month);
//        if (totalCustomers == 0) {
//            return 0.0;
//        }
//        Long subscribedCustomers = subscriptionRepository.countSubscribedCustomers(year, month);
//        return (double) subscribedCustomers / totalCustomers * 100;
//    }
//}
