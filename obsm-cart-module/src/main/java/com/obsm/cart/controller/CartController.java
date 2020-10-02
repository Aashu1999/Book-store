package com.obsm.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obsm.cart.exception.CartNotFound;
import com.obsm.cart.model.Book;
import com.obsm.cart.model.CustomerCart;

import com.obsm.cart.service.ICartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4200")
public class CartController {
	@Autowired
	ICartService service;

	// localhost:8110/cart/addtoCart as post
	// adding the cart details
	@PostMapping("/addtoCart")
	public ResponseEntity<?> addCart(@RequestBody CustomerCart cart) {
		return (service.addCart(cart));
	}

	// localhost:8110/cart/deletefromCart/{id} as delete
	// deleting the cart details
	@DeleteMapping("/deletefromCart/{cartId}")
	public ResponseEntity<?> deletecart(@PathVariable long cartId) throws CartNotFound {
		return service.deleteCart(cartId);
	}

	// localhost:8110/cart/updateCart/{cartId}
	// updating the existing cart details
	@PutMapping("/updateCart/{cartId}")
	public ResponseEntity<?> updateCart(@RequestBody CustomerCart cart, @PathVariable long cartId) throws CartNotFound {
		return service.updateCart(cart, cartId);
	}

	// localhost:8110/cart/getCart/{cartId}
	// getting the specific card details
	@GetMapping("/getCart/{cartId}")
	public ResponseEntity<?> getCart(@PathVariable long cartId) throws CartNotFound {
		return service.getCart(cartId);
	}

	// localhost:8110/cart/getAllCarts
	// getting all the card details
	@GetMapping("/getAllCarts")
	public List<CustomerCart> getAllCarts() {
		return service.getAllCarts();
	}

	@GetMapping("/getCartByCustomerId/{customerId}")
	public ResponseEntity<?> getTask(@PathVariable long customerId) {
		return service.getCartByCustomerId(customerId);
	}

	// localhost:8110/cart/getAllBooks
	// displaying all the books
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}
}
