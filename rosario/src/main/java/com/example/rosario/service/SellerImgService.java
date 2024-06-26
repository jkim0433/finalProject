package com.example.rosario.service;

import com.example.rosario.entity.Seller;
import com.example.rosario.entity.SellerImg;
import com.example.rosario.repository.SellerImgRepository;
import com.example.rosario.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerImgService {
    @Autowired
    private SellerImgRepository sellerImgRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public SellerImg saveFile (Long sellerId, String filename, String filePath){
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        if (sellerOpt.isPresent()){
            Seller seller = sellerOpt.get();
            SellerImg sellerImg = new SellerImg(seller, filename, filePath);
            return sellerImgRepository.save(sellerImg);
        } else {
            throw new RuntimeException("Seller not found with id:" + sellerId);
        }
    }

    public List<SellerImg> getImagesBySellerId(Long sellerId) {
        return sellerImgRepository.findBySellerSellerId(sellerId);
    }

    public SellerImg updateFile(Long sellerId, String filename, String filePath) throws Exception {
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        if (!sellerOpt.isPresent()) {
            throw new Exception("판매자를 찾을 수 없습니다.");
        }
        Seller seller = sellerOpt.get();

        Optional<SellerImg> existingImgOpt = sellerImgRepository.findBySellerAndSellerFilename(seller, filename);
        if (!existingImgOpt.isPresent()) {
            throw new Exception("이미지를 찾을 수 없습니다.");
        }

        SellerImg existingImg = existingImgOpt.get();
        existingImg.setSellerFilePath(filePath);
        return sellerImgRepository.save(existingImg);
    }
}
