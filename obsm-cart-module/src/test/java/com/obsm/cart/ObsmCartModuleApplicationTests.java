package com.obsm.cart;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.obsm.cart.model.CustomerCart;
import com.obsm.cart.model.Status;
import com.obsm.cart.repository.CartRepository;
import com.obsm.cart.service.CartService;


@SpringBootTest
class ObsmCartModuleApplicationTests {
	
	@MockBean
	private CartService service;
	


	//Here we check if the getCart by id is working correctly or not
	@Test 
	public void testGetCart() {
	   service.getCart(2);
	   verify(service,times(1)).getCart(2);
	  }

	//Here we check if the DeleteCart by id is working correctly or not
	@Test
	public void testDeleteCart() {
		service.deleteCart(2);
		verify(service,times(1)).deleteCart(2);
	}
	//Here we check if the GetAllCarts is working correctly or not
	@Test 
	public void testGetAllCarts() {
	   service.getAllCarts();
	   verify(service,times(1)).getAllCarts();
	  }
	
	//Here we check if the AddCart by id is working correctly or not
	@Test
	public void testAddCartDetails() {
		CustomerCart cart=new CustomerCart();
		cart.setCartId(2);
		cart.setCustomerId(3);
		cart.setOrderId(345);
		cart.setBookId(212);
		cart.setNumberOfBooks(2);
		cart.setTotalCost(2000.00);
		cart.setCartStatus(Status.NOT_ACCEPTED);
	}
}
