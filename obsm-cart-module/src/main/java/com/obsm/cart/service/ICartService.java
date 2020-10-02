package com.obsm.cart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.obsm.cart.exception.CartNotFound;
import com.obsm.cart.model.Book;
import com.obsm.cart.model.CustomerCart;


public interface ICartService {
	public ResponseEntity<?> addCart(CustomerCart cart);

	public ResponseEntity<?> deleteCart(long cartId) throws CartNotFound;

	public ResponseEntity<?> getCart(long cartId) throws CartNotFound;

	public List<CustomerCart> getAllCarts();

	public ResponseEntity<?> updateCart(CustomerCart cart, long cartId);

	public ResponseEntity<CustomerCart> getCartByCustomerId(long customerId) throws CartNotFound;
	
	public List<Book> getAllBooks();


}
