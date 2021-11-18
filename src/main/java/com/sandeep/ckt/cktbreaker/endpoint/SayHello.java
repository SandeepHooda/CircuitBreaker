package com.sandeep.ckt.cktbreaker.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class SayHello {
	
	private static final String ORDER_SERVICE = "HELLO_SERVICE";
	 private static int count=0;
	
	@GetMapping("/order")
    @CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "orderFallback")
    public ResponseEntity<String> createOrder(){
		
		count++;
		RestTemplate restTemplate = new RestTemplate();
		String response  = "Downstream service is working fine";
		if (count > 3) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response= restTemplate.getForObject("http://localhost:8081/item", String.class);
		}
        
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    public ResponseEntity<String> orderFallback(Exception e){
        return new ResponseEntity<String>("Downstream service is down", HttpStatus.OK);

    }


}
