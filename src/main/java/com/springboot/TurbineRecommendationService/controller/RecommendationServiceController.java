package com.springboot.TurbineRecommendationService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.TurbineRecommendationService.pojo.Product;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RecommendationServiceController {
	
	private static List<Product> productList = new ArrayList<>();
	static {
		productList.add(new Product("product-1", "description-1", "link-1"));
		productList.add(new Product("product-2", "description-2", "link-2"));
        productList.add(new Product("product-3", "description-3", "link-3"));
    }
	
	@GetMapping("/recommendations")
	@HystrixCommand(fallbackMethod = "recommendationFallback")
	public ResponseEntity<?> recommendations() {
        return ResponseEntity.ok(productList);
    }

	 public ResponseEntity<?> recommendationFallback() {
		    log.info("into recommendationFallback method=========");
		    return ResponseEntity.ok(productList.get(0));
		}
}
