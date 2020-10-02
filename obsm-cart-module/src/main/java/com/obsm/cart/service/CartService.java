package com.obsm.cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.obsm.cart.exception.CartNotFound;

import com.obsm.cart.model.Book;
import com.obsm.cart.model.BookList;
import com.obsm.cart.model.CustomerCart;

import com.obsm.cart.repository.CartRepository;

@Service
@Transactional
public class CartService implements ICartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	RestTemplate rt;

	/*************************************************************************
	 * -MethodName : addCart -Input Parameters : CustomerCart cart -Return Type :
	 * cart -Throws : CartNotFound -Author : Ashutosh Dhondi -Modified Date :
	 * 23/09/2020
	 ***************************************************************************/
	// Adding the cart details to the database by using CartRepository
	@Override
	public ResponseEntity<?> addCart(CustomerCart cart) {
		try {
			if (cart != null) {
				return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.CREATED);
			} else
				throw new CartNotFound("Not a valid request");
		} catch (CartNotFound cartNotFound) {
			String message = cartNotFound.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	/*************************************************************************
	 * -MethodName : updateCart -Input Parameters : CustomerCart cart, long cartId
	 * -Return Type : cart -Throws : CartNotFound -Author : Ashutosh Dhondi
	 * -Modified Date : 23/09/2020
	 ***************************************************************************/
	// updating the existing cart details to the database by using CartRespository
	@Override
	public ResponseEntity<?> updateCart(CustomerCart cart, long cartId) {
		try {
			if (cartRepository.existsById(cartId)) {
				CustomerCart updatedCart = cartRepository.getOne(cartId);
				updatedCart.setBookId(cart.getBookId());
				updatedCart.setCartStatus(cart.getCartStatus());
				updatedCart.setCustomerId(cart.getCustomerId());
				updatedCart.setNumberOfBooks(cart.getNumberOfBooks());
				updatedCart.setOrderId(cart.getOrderId());
				updatedCart.setTotalCost(cart.getTotalCost());

				return new ResponseEntity<>(cartRepository.save(updatedCart), HttpStatus.ACCEPTED);
			} else
				throw new CartNotFound("invalid cart id");

		} catch (CartNotFound cartNotFound) {
			String message = cartNotFound.getMessage();
			return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/*************************************************************************
	 * -MethodName : getCart -Input Parameters : long cartId -Return Type : cart
	 * -Throws : CartNotFound , InvalidCartId -Author : Ashutosh Dhondi -Modified
	 * Date : 23/09/2020
	 ***************************************************************************/
	// getting the specific cart details from the database by using CartRepository
	@Override
	public ResponseEntity<?> getCart(long cartId) throws CartNotFound {
		try {
			CustomerCart cart = cartRepository.getOne(cartId);
			if (cartRepository.existsById(cartId))
				return new ResponseEntity<>(cart, HttpStatus.FOUND);
			else
				throw new CartNotFound("No cart is available");
		} catch (CartNotFound cartNotFound) {
			String message = cartNotFound.getMessage();
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

		} catch (Exception exception) {
			return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
		}
	}

	/*************************************************************************
	 * -MethodName : getAllCarts -Input Parameters : none -Return Type :
	 * List<CustomerCart> -Throws : None -Author : Ashutosh Dhondi -Modified Date :
	 * 23/09/2020
	 ***************************************************************************/

	// getting all the cart details from the database by using CartRepository
	@Override
	public List<CustomerCart> getAllCarts() {
		return cartRepository.findAll();
	}

	/*************************************************************************
	 * -MethodName : deleteCart -Input Parameters : long CartId -Return Type :
	 * String -Throws : CartNotFound -Author : Ashutosh Dhondi -Modified Date :
	 * 23/09/2020
	 ***************************************************************************/
	// deleting the cart details from the database by using CartRepository
	@Override
	public ResponseEntity<String> deleteCart(long cartId) throws CartNotFound {
		try {
			CustomerCart deletedcart = cartRepository.getOne(cartId);
			if (cartRepository.existsById(cartId)) {
				cartRepository.delete(deletedcart);
				return new ResponseEntity<>(HttpStatus.OK);
			} else
				throw new CartNotFound("cart is Null");
		} catch (CartNotFound cartNotFound) {
			String message = cartNotFound.getMessage();
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} catch (Exception exception) {
			String message = exception.getMessage();

			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CustomerCart> getCartByCustomerId(long customerId) throws CartNotFound {
		try {
			CustomerCart cart = cartRepository.findByCustomerId(customerId);
			if (cart != null)
				return new ResponseEntity<>(cart, HttpStatus.FOUND);
			else
				throw new CartNotFound("No cart is available");
		} catch (CartNotFound cartNotFound) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// This method getAllBooks functionality is taken from the obsm-book-module
	// using Rest Template
	// This method is used in the front end part i.e angular
	// In this method we get the details of books from the BookList Class and the
	// details can be displayed
	// in both angular and postman
	@Override
	public List<Book> getAllBooks() {
		BookList booklist = new BookList();
		booklist = rt.getForObject("http://localhost:8140/bookinfo/getAllBooks", BookList.class);
		return booklist.getList();
	}

}
