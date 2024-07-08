package com.example.rosario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.rosario")// JwtProvider 클래스가 스캔추가(07.06)
public class RosarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosarioApplication.class, args);
	}

}
