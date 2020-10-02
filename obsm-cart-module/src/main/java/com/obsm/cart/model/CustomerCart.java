package com.obsm.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cart_data")
public class CustomerCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;
	@Column
	@NotNull
	private long customerId;
	@Column
	@NotNull
	private long orderId;
	@Column
	@NotNull
	private long bookId;
	@Column
	@NotNull
	private long numberOfBooks;
	@Column
	@NotNull
	private double totalCost;
	@Column
	@NotNull
	private Status cartStatus = Status.NOT_ACCEPTED;

	public long getCartId() {
		return cartId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		result = prime * result + (int) (cartId ^ (cartId >>> 32));
		result = prime * result + ((cartStatus == null) ? 0 : cartStatus.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + (int) (numberOfBooks ^ (numberOfBooks >>> 32));
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerCart other = (CustomerCart) obj;
		if (bookId != other.bookId)
			return false;
		if (cartId != other.cartId)
			return false;
		if (cartStatus != other.cartStatus)
			return false;
		if (customerId != other.customerId)
			return false;
		if (numberOfBooks != other.numberOfBooks)
			return false;
		if (orderId != other.orderId)
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		return true;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(long numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Status getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(Status cartStatus) {
		this.cartStatus = cartStatus;
	}

	public CustomerCart() {
	}

	@Override
	public String toString() {
		return "CustomerCart [cartId=" + cartId + ", customerId=" + customerId + ", orderId=" + orderId + ", bookId="
				+ bookId + ", numberOfBooks=" + numberOfBooks + ", totalCost=" + totalCost + ", cartStatus="
				+ cartStatus + "]";
	}
}
