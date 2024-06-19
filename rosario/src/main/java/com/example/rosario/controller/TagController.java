package com.example.rosario.controller;

import com.example.rosario.entity.CustomerTag;
import com.example.rosario.entity.ChipTag;
import com.example.rosario.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    // 새로운 chipTag 생성
    @PostMapping("/chipTags")
    public ChipTag createChipTag(@RequestBody String label) {
        return tagService.createChipTag(label);
    }
    // 모든 chipTag 조회
    @GetMapping("/chipTags")
    public List<ChipTag> getAllChipTags() {
        return tagService.getAllChipTags();
    }
    // chipTag 삭제
    @DeleteMapping("/chipTags/{chipTagId}")
    public void deleteChipTag(@PathVariable("chipTagId") String chipTagId) {
        tagService.deleteChipTag(Long.parseLong(chipTagId));
    }

    // customerId로 chipTag 조회
    @GetMapping("/customers/{customerId}/chipTags")
    public List<CustomerTag> getChipTagsByCustomerId(@PathVariable("customerId") String customerId) {
        return tagService.getChipTagsByCustomerId(Long.parseLong(customerId));
    }

    // 고객에게 chipTag 추가
    @PostMapping("/customers/{customerId}/chipTags/{chipTagId}")
    public CustomerTag addChipTagToCustomer(@PathVariable("customerId") String customerId, @PathVariable("chipTagId") String chipTagId) {
        return tagService.addChipTagToCustomer(Long.parseLong(customerId), Long.parseLong(chipTagId));
    }

    // 고객에게 여러 chipTag 추가
    @PostMapping("/customers/{customerId}/chipTags")
    public List<CustomerTag> addMultipleChipTagsToCustomer(@PathVariable("customerId") String customerId, @RequestBody List<Long> chipTagIds) {
        return tagService.addMultipleChipTagsToCustomer(Long.parseLong(customerId), chipTagIds);
    }

    //고객의 모든 chipTag 조회
    @GetMapping("/customers/{customerId}/allChipTags")
    public List<ChipTag> getAllChipTagsByCustomerId(@PathVariable("customerId") String customerId) {
        return tagService.getAllChipTagsByCustomerId(Long.parseLong(customerId));
    }
}
