package com.bookstoreapi.controller;

import com.bookstoreapi.dao.OrderDAO;
import com.bookstoreapi.dao.OrderRequest;
import com.bookstoreapi.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "orders", description = "The Orders API")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @Operation(description = "Save An Order", tags = {"orders"})
    @PostMapping
    public ResponseEntity<OrderDAO> save(@RequestBody OrderRequest orderRequest) {

        return ResponseEntity.ok(orderService.save(orderRequest));
    }


    @Operation(description = "Get All Orders By User ID", tags = {"orders"})
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDAO>> getAllByUserId(@PathVariable(value = "userId") long userId) {

        return ResponseEntity.ok(orderService.getAllByUserId(userId));
    }


    @Operation(description = "Get Order By Order ID", tags = {"orders"})
    @GetMapping("details/{orderId}")
    public ResponseEntity<OrderDAO> getByOrderId(@PathVariable(value = "orderId") long orderId) {
        OrderDAO orderDAO = orderService.getById(orderId);
        return new ResponseEntity<>(orderDAO, HttpStatus.OK);
    }

}
