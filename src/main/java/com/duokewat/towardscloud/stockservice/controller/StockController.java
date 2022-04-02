package com.duokewat.towardscloud.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
	@GetMapping("/summary")
    public String getAllBook() {
        return "Hello Stock";
    }

}
