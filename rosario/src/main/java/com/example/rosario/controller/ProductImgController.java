package com.example.rosario.controller;

import com.example.rosario.entity.ProductImg;
import com.example.rosario.service.ProductImgService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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

@RestController
@RequestMapping("/api/uploadProdImg")
@CrossOrigin(origins = "http://localhost:5173") // 리액트 개발 서버의 주소로 CORS 설정
public class ProductImgController {
    @Value("${upload.path}")
    private String uploadDir;

    @Autowired
    private ProductImgService productImgService;

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
    public Map<String, Object> uploadFile( @RequestParam("file") MultipartFile file) {
        try {
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
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
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @GetMapping("/{productId}")
    public List<ProductImg> getImagesByProductId(@PathVariable("productId") String productId) {
        return productImgService.getImagesByProductId(Long.parseLong(productId));
    }

    @PostMapping("/p/{productId}")
    public ProductImg saveFileUri(@PathVariable("productId") String productId,
                                  @RequestBody Map<String, String> body) {
        String filename = body.get("filename");
        String fileUri = body.get("fileUri");
        return productImgService.saveFile(Long.parseLong(productId), filename, fileUri);
    }
}
