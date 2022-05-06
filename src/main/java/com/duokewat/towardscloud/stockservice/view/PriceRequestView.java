package com.duokewat.towardscloud.stockservice.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class PriceRequestView {
	private String stock;
	private String market;
}
