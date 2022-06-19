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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/market")
@Slf4j
public class MarketPriceController {
	@Autowired
	private MarketPriceConsumer marketPriceConsumer;
	@Autowired
	private PriceResponseView priceResponseView;

	@PostMapping("/price")
	public PriceResponseView getRegularMarketPrice(@RequestBody PriceRequestView priceRequestView) {
		log.debug("Inside getRegularMarketPrice()");
		log.debug("priceRequestView {}",priceRequestView);
		BigDecimal regularMarketPrice = marketPriceConsumer.getPrice(priceRequestView);

		priceResponseView.setMarket(priceRequestView.getMarket());
		priceResponseView.setStock(priceRequestView.getStock());
		priceResponseView.setRegularMarketPrice(regularMarketPrice);
		
		log.debug("priceResponseView {}",priceResponseView);
		return priceResponseView;
	}

	@RequestMapping("/ruok")
	public String ruok() {
		return "Yes, I am Okay !";
	}
}
