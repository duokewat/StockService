mvn spring-boot:build-image

curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://localhost:8081/actuator/loggers/com.duokewat.towardscloud.stockservice.controller


URL
***
http://localhost:9081/market/price

Request
*******
{
	"stock":"AAPL",
	"market":"NASDAQ"

}

Response
********
{
    "stock": "AAPL",
    "market": "NASDAQ",
    "regularMarketPrice": 132
}