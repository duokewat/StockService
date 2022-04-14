package com.duokewat.towardscloud.stockservice.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.duokewat.towardscloud.stockservice.json.JsonResponse;
import com.duokewat.towardscloud.stockservice.view.PriceRequestView;

@Component
public class MarketPriceConsumer {

	@Value("${stock.host}")
	private String apiHost;

	@Value("${stock.key}")
	private String key;

	@Value("${stock.url}")
	private String url;

	public Long getPrice(PriceRequestView priceRequestView) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HttpHeaderInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HttpHeaderInterceptor("X-RapidAPI-Host", apiHost));
		interceptors.add(new HttpHeaderInterceptor("X-RapidAPI-Key", key));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);

		System.out.println(getUrl(priceRequestView));
		System.out.println(apiHost);
		System.out.println(key);
		
		JsonResponse jsonResponse = restTemplate.getForObject(getUrl(priceRequestView), JsonResponse.class);
		return jsonResponse.getQuoteResponse().getResult().get(0).getRegularMarketPrice();
	}

	private String getUrl(PriceRequestView request) {
		return String.format(url, request.getMarket(), request.getStock());
	}
}
