package com.example.rosario.controller;

import com.example.rosario.entity.SellerImg;
import com.example.rosario.service.SellerImgService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 판매자 upload 이미지
@RestController
@RequestMapping("/api/uploadSellerImg")
@CrossOrigin(origins = "http://localhost:5173")
public class SellerImgController {
    @Value("${upload.path}")
    private String uploadDir;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SellerImgService sellerImgService;

    @PostConstruct
    public void init() {
        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }
    @PostMapping("/test")
    public Map<String, Object> uploadFile (@RequestParam("file")MultipartFile file){
        try{
            String filename =System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + filename);
            Files.write(path, file.getBytes());

            // 파일 URL 생성
            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(filename)
                    .toUriString();

            // 응답 데이터 생성
            Map<String, Object> response = new HashMap<>();
            response.put("fileUri", fileUri);
            response.put("fileName", filename);


            return response;
        } catch (IOException e){
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @GetMapping("/{sellerId}")
    public List<SellerImg> getImagesBySellerId(@PathVariable("sellerId") String sellerId){
        return sellerImgService.getImagesBySellerId(Long.parseLong(sellerId));
    }

    @PostMapping("/s/{sellerId}")
    public ResponseEntity<?> saveFileUri(@PathVariable("sellerId") Long sellerId, @RequestBody Map<String, String> body) {
        String filename = body.get("filename");
        String fileUri = body.get("fileUri");

        logger.info("Received request to save file URI for sellerId: {}", sellerId);
        logger.info("Filename: {}", filename);
        logger.info("File URI: {}", fileUri);

        if (filename == null || fileUri == null) {
            return ResponseEntity.badRequest().body("filename과 fileUri를 제공해야 합니다.");
        }

        try {
            SellerImg sellerImg = sellerImgService.saveFile(sellerId, filename, fileUri);
            return ResponseEntity.ok(sellerImg);
        } catch (Exception e) {
            logger.error("파일 URI 저장 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/s/{sellerId}")
    public ResponseEntity<?> updateFilePath(@PathVariable("sellerId") Long sellerId, @RequestBody Map<String, String> body) {
        String filename = body.get("filename");
        String fileUri = body.get("fileUri");

        logger.info("Received request to update file path for sellerId: {}", sellerId);
        logger.info("Filename: {}", filename);
        logger.info("File Path: {}", fileUri);

        if (filename == null || fileUri == null) {
            return ResponseEntity.badRequest().body("filename과 filePath를 제공해야 합니다.");
        }

        try {
            SellerImg updatedSellerImg = sellerImgService.updateFile(sellerId, filename, fileUri);
            return ResponseEntity.ok(updatedSellerImg);
        } catch (Exception e) {
            logger.error("파일 경로 업데이트 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
