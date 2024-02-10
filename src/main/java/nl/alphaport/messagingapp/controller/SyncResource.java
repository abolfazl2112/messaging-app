package nl.alphaport.messagingapp.controller;

import nl.alphaport.messagingapp.dto.request.SyncProductRequest;
import nl.alphaport.messagingapp.service.rabbitmq.Producer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-24   4:02 PM
 */

@RequestMapping("/api/sync")
@RestController
public class SyncResource {

    private Producer producer;

    public SyncResource(Producer producer) {
        this.producer = producer;
    }

    /**
     * sync all products with online shop
     * @param authorization contains db info (username,password, ...)
     * @param request webshop type, from date for update
     * @return
     */
    @PostMapping("/v1/sync-products")
    public ResponseEntity<Boolean> syncProducts(@RequestHeader(HttpHeaders.AUTHORIZATION)String authorization, @RequestBody SyncProductRequest request){
        System.out.print(authorization);
        producer.Send(request);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/v1/delay/sync-products")
    public ResponseEntity<Boolean> syncProductsWithDelay(@RequestHeader(HttpHeaders.AUTHORIZATION)String authorization, @RequestBody SyncProductRequest request){
        System.out.print(authorization);
        producer.sendWithDelay(request,10000);
        return ResponseEntity.ok(true);
    }

//    @PostMapping("/v1/sync")
//    public ResponseEntity<Map<String, String>> syncData(@RequestBody RegisterUserDto registerUserDto){
//
//        sender.Send(registerUserDto);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "User registered successfully!");
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/v1/sync-status")
//    public ResponseEntity<Map<String, String>> syncStatus(){
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "User registered successfully!");
//        return ResponseEntity.ok(response);
//    }
}
