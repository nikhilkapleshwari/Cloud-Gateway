package io.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.api.common.TransactionRequest;
import io.api.common.TransactionResponse;

@Service
@RefreshScope
public class GatewayService {

  @Autowired
  RestTemplate restTemplate;
  
  @Value("${${microservice.order-service.endpoints.endpoint.uri:abc}}")
  private String ORDER_SERVICE_ENDPOINT;
  
  @HystrixCommand(fallbackMethod="defaultOrder",
	  				commandProperties= {
	  					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000"),
	                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="4"),
	                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
	                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
	  				})
  public TransactionResponse bookOrderService(TransactionRequest request) {
	//return restTemplate.postForObject("http://ORDER-SERVICE/order/bookOrder", request, TransactionResponse.class);
	System.out.println("ORDER_SERVICE endpoint resolved to: "+ORDER_SERVICE_ENDPOINT);
	return restTemplate.postForObject(ORDER_SERVICE_ENDPOINT, request, TransactionResponse.class);
  }
  
  public TransactionResponse defaultOrder(TransactionRequest request) {
	System.out.println("defaultOrder is sending");
	TransactionResponse transactionResponse = new TransactionResponse();
	transactionResponse.setMessage("Currently not processing orders");
	return transactionResponse;
  }
}
