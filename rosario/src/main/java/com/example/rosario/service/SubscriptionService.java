package com.example.rosario.service;

import com.example.rosario.dto.OrdersDto;
import com.example.rosario.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 구독자 및 비구독자 퍼센트, 리스트  service
@Service
public class SubscriptionService {

    @Autowired
    private OrdersRepository ordersRepository;

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
