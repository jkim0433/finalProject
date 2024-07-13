package com.example.rosario.controller;
import com.example.rosario.dto.OrdersDto;
import com.example.rosario.dto.SubscriptionDto;
import com.example.rosario.entity.Subscription;
import com.example.rosario.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


// 구독과 관련된 %, 리스트 관련 controller
@RestController
@RequestMapping("/api/subscribes")
public class SubscriptionController {

    //모든 subscribes내용 조회(도경추가0712)----------------------------
    @GetMapping
    public List<SubscriptionDto> getAllSubscription(){return subscriptionService.getAllSubscription();}
    //-------------------------------------------------------------

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/type/monthly/{year}/{month}")
    public ResponseEntity<MonthlyChartData> getMonthlyChart(@PathVariable("year") int year, @PathVariable("month") int month) {
        MonthlyChartData chartData = new MonthlyChartData();

        // 구독 퍼센트 계산
        double subscriptionPercentage = subscriptionService.calculateSubscriptionPercentage(year, month);
        double unsubscribedPercentage = 100 - subscriptionPercentage;

        // 데이터 설정
        chartData.setSubscribedPercentage(subscriptionPercentage);
        chartData.setUnsubscribedPercentage(unsubscribedPercentage);

        // 구독 조회 및 설정
        List<OrdersDto> subscribedList = subscriptionService.getSubscribedList(year, month);

        // 비구독 조회 및 설정
        List<OrdersDto> unsubscribedList = subscriptionService.getUnsubscribedList(year, month);

        chartData.setSubscribedList(subscribedList);
        chartData.setUnsubscribedList(unsubscribedList);

        return ResponseEntity.ok(chartData);
    }


    // 분기, 연도별 차트에 대한 API도 유사하게 구현 가능

    // 구독자 리스트 조회
    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionDto>>getSubscriptionList(){
        List<SubscriptionDto> subscriptionList = subscriptionService.getSubscriptionList();
                return ResponseEntity.ok(subscriptionList);
    }


    // SellerId를 기준으로 구독자 리스트 조회
    @GetMapping("/subscriptions/{sellerId}")
    public ResponseEntity<List<SubscriptionDto>>getSubscriptionListBySellerId(@PathVariable("sellerId") Long sellerId) {
       List<SubscriptionDto>subscriptionList = subscriptionService.getSubscriptionListBySellerId(sellerId);
        return ResponseEntity.ok(subscriptionList);
        //HTTP 상태 코드 200(OK)를 클라이언트에게 반환
    }

}
