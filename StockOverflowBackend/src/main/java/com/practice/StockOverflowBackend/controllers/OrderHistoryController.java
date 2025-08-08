package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.services.OrderHistoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderHistoryController {

    private final OrderHistoryService service;

    public OrderHistoryController(OrderHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Order_History>> getAllOrders() {
        List<Order_History> orders = service.getAllOrders();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(orders.size())); // custom header example

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order_History> getOrderById(@PathVariable int id) {
        Order_History order = service.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Order-Found", "true");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(order);
    }

    @PostMapping
    public ResponseEntity<Order_History> createOrder(@RequestBody Order_History order) {
        if (order.getStock() != null) {
            System.out.println("Incoming symbolId: " + order.getStock().getSymbol_id());
        } else {
            System.out.println("Incoming stock is NULL");
        }
        Order_History savedOrder = service.saveOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Created-Id", String.valueOf(savedOrder.getOrderId()));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(savedOrder);
    }
    @GetMapping("/search")
    public List<Order_History> searchOrdersByStockName(@RequestParam String name) {
        return service.searchOrdersByStockNameOrSymbol(name);
    }


}
