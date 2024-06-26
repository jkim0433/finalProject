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

        // sellerId와 일치하는 이미지들을 모두 조회
        List<SellerImg> sellerImages = sellerImgRepository.findBySellerSellerId(sellerId);

        // filename과 일치하는 이미지 중 첫 번째 이미지를 업데이트하거나 새로 생성
        Optional<SellerImg> existingImgOpt = sellerImages.stream()
                .filter(img -> img.getSellerFilename().equals(filename))
                .findFirst();

        SellerImg updatedImg;
        if (existingImgOpt.isPresent()) {
            // 기존 이미지가 존재하면 filePath 업데이트
            SellerImg existingImg = existingImgOpt.get();
            existingImg.setSellerFilePath(filePath);
            updatedImg = sellerImgRepository.save(existingImg);
        } else {
            // 기존 이미지가 없으면 새로 생성
            SellerImg newImg = new SellerImg();
            newImg.setSeller(seller);
            newImg.setSellerFilename(filename);
            newImg.setSellerFilePath(filePath);
            updatedImg = sellerImgRepository.save(newImg);
        }

        return updatedImg; // 적절히 updatedImg를 반환하도록 설정해야 합니다.
    }


}
