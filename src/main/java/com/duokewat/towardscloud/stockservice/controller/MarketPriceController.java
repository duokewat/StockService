package com.duokewat.towardscloud.stockservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duokewat.towardscloud.stockservice.consumer.MarketPriceConsumer;
import com.duokewat.towardscloud.stockservice.view.PriceRequestView;
import com.duokewat.towardscloud.stockservice.view.PriceResponseView;

@RestController
@RequestMapping("/market")
public class MarketPriceController {
	@Autowired
	private MarketPriceConsumer marketPriceConsumer;
	@Autowired
	private PriceResponseView priceResponseView;
	
	@PostMapping("/price")
	public PriceResponseView getRegularMarketPrice(@RequestBody PriceRequestView priceRequestView) {
		BigDecimal regularMarketPrice = marketPriceConsumer.getPrice(priceRequestView);
		
		priceResponseView.setMarket(priceRequestView.getMarket());
		priceResponseView.setStock(priceRequestView.getStock());
		priceResponseView.setRegularMarketPrice(regularMarketPrice);
		return priceResponseView;
	}
}
