package com.duokewat.towardscloud.stockservice.consumer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.duokewat.towardscloud.stockservice.json.JsonResponse;
import com.duokewat.towardscloud.stockservice.view.PriceRequestView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MarketPriceConsumer {

	@Value("${stock.host}")
	private String apiHost;

	@Value("${stock.key}")
	private String key;

	@Value("${stock.url}")
	private String url;

	public BigDecimal getPrice(PriceRequestView priceRequestView) {
		log.debug("Inside getPrice()");
		log.debug("apiHost '{}'",apiHost);
		log.debug("key '{}'",key);
		log.debug("url '{}'",url);
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Host", apiHost));
		interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Key", key));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		
		String finalUrl = getUrl(priceRequestView);
		log.debug("finalUrl '{}'",finalUrl);
		
		JsonResponse jsonResponse = restTemplate.getForObject(finalUrl, JsonResponse.class);
		log.debug("Post Cloud API call");
		
		BigDecimal price = jsonResponse.getQuoteResponse().getResult().get(0).getRegularMarketPrice();
		log.debug("price '{}'",price);
		
		return price;
	}

	private String getUrl(PriceRequestView request) {
		return String.format(url, request.getMarket(), request.getStock());
	}
}
