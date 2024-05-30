package com.proyecto.ecommerce.backend.infraestructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ecommerce.backend.application.OrderService;
import com.proyecto.ecommerce.backend.domain.model.Order;
import com.proyecto.ecommerce.backend.domain.model.OrderState;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
@Slf4j
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<Order> save(@RequestBody Order order) {
		
		if (order.getOrderState().toString().equals(OrderState.CANCELLED.toString())) {
			order.setOrderState(OrderState.CANCELLED);
		} else {
			order.setOrderState(OrderState.CONFIRMED);

		}
		return ResponseEntity.ok(orderService.save(order));
	}
	
	@PostMapping("/update/state/order")
	public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state) {
		orderService.updateStateById(id, state);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable <Order>> findAll() {
		orderService.findAll();
		return ResponseEntity.ok(orderService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> findById(@PathVariable("id")Integer id){
		return ResponseEntity.ok(orderService.findById(id));
	}
	
	@GetMapping("/by-user/{userId}")
	public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable("userId")Integer userId){
		return ResponseEntity.ok(orderService.findByUserId(userId));
	}

}
