package com.example.rosario.controller;

import com.example.rosario.dto.DeliveryDto;
import com.example.rosario.entity.Delivery;
import com.example.rosario.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryDto> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{deliveryId}")
    public Delivery getDeliveryById(@PathVariable Long deliveryId){
        return deliveryService.getDeliveryById(deliveryId);
    }

    //Other endpoints for CRUD operations
}
