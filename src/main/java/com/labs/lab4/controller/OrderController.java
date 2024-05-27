package com.labs.lab4.controller;

import com.labs.lab4.dto.request.CreateOrderRequest;
import com.labs.lab4.dto.request.OrderReportRequest;
import com.labs.lab4.dto.request.PatchOrderRequest;
import com.labs.lab4.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/rec-id")
    public ResponseEntity<?> getRecId() {
        return orderService.getRecId();
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.create(createOrderRequest);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> patchOrder(@PathVariable BigInteger orderId, @RequestBody PatchOrderRequest patchOrderRequest) {
        return orderService.patch(orderId, patchOrderRequest);
    }

    @PatchMapping("/close/{orderId}")
    public ResponseEntity<?> closeOrder(@PathVariable BigInteger orderId) {
        return orderService.close(orderId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getForReport(@RequestBody OrderReportRequest orderReportRequest) {
        return orderService.getForReport(orderReportRequest);
    }
}
