package com.duokewat.towardscloud.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duokewat.towardscloud.stockservice.consumer.ExchangeServiceConsumer;
import com.duokewat.towardscloud.stockservice.view.ExchangeRequest;
import com.duokewat.towardscloud.stockservice.view.ExchangeResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/currency")
@Slf4j
public class ExchangeController {
	
	@Autowired
	ExchangeServiceConsumer exchangeServiceConsumer;
	
	@Autowired
	ExchangeResponse exchangeResponse;
	
	@PostMapping("/exchange")
    public ExchangeResponse getExchangedDetail(@RequestBody ExchangeRequest request) {
		log.info("getExchangedDetail() Begins");
	    log.debug("ExchangeRequest {} " , request);
		exchangeResponse.setFrom(request.getFrom());
		exchangeResponse.setTo(request.getTo());
		exchangeResponse.setValue(exchangeServiceConsumer.getExchangedDetail(request));
		return exchangeResponse;
    }
}
