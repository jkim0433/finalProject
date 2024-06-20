package com.example.rosario.service;

import com.example.rosario.entity.Customer;
import com.example.rosario.entity.CustomerTag;
import com.example.rosario.entity.ChipTag;
import com.example.rosario.repository.CustomerTagRepository;
import com.example.rosario.repository.ChipTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private CustomerTagRepository customerTagRepository;

    @Autowired
    private ChipTagRepository chipTagRepository;

    // 새로운 chipTag 생성
    public ChipTag createChipTag(String label) {
        ChipTag chipTag = new ChipTag();
        chipTag.setLabel(label);
        return chipTagRepository.save(chipTag);
    }

    // 모든 chipTag 조회
    public List<ChipTag> getAllChipTags() {
        return chipTagRepository.findAll();
    }

    // chipTag 삭제
    public void deleteChipTag(Long chipTagId) {
        chipTagRepository.deleteById(chipTagId);
    }

    // customerId로 chipTag 조회
    public List<CustomerTag> getChipTagsByCustomerId(Long customerId) {
        return customerTagRepository.findByCustomerCustomerId(customerId);
    }

    // 고객에게 chipTag 추가
    public CustomerTag addChipTagToCustomer(Long customerId, Long chipTagId) {
        CustomerTag customerTag = new CustomerTag();
        customerTag.setCustomer(new Customer(customerId)); // Customer 클래스에 생성자가 있다고 가정
        customerTag.setChipTag(new ChipTag(chipTagId)); // ChipTag 클래스에 생성자가 있다고 가정
        return customerTagRepository.save(customerTag);
    }

    // 고객에게 여러 chipTag 추가
    public List<CustomerTag> addMultipleChipTagsToCustomer(Long customerId, List<Long> chipTagIds) {
        List<CustomerTag> customerTags = new ArrayList<>();
        for (Long chipTagId : chipTagIds) {
            CustomerTag customerTag = new CustomerTag();
            customerTag.setCustomer(new Customer(customerId));
            customerTag.setChipTag(new ChipTag(chipTagId));
            customerTags.add(customerTagRepository.save(customerTag));
        }
        return customerTags;
    }
    // 특정 고객의 모든 ChipTag 조회
    public List<ChipTag> getAllChipTagsByCustomerId(Long customerId) {
        List<CustomerTag> customerTags = customerTagRepository.findByCustomerCustomerId(customerId);
        return customerTags.stream()
                .map(CustomerTag::getChipTag)
                .collect(Collectors.toList());
    }
}
