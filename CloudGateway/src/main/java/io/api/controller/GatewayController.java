package io.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.api.common.TransactionRequest;
import io.api.common.TransactionResponse;
import io.api.service.GatewayService;

@RestController
@RequestMapping("/api")
public class GatewayController {

  @Autowired
  GatewayService gatewayService;
  
  @PostMapping("/order/bookOrder")
  public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
    System.out.println("order call received...");
	return gatewayService.bookOrderService(request);
  }


  @GetMapping("/hello")
  public String sayHello() {
	return "Hello!";
  }
}
