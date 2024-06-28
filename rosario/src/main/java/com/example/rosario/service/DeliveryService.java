package com.example.rosario.service;

import com.example.rosario.entity.Delivery;
import com.example.rosario.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAllDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long deliveryId){
        return deliveryRepository.findById(deliveryId).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    //Other methods for CRUD operations
}
