package com.obsm.cart.model;

import java.util.List;

public class BookList {
	public BookList() {
		super();
		
	}

	public BookList(List<Book> list) {
		super();
		this.list = list;
	}

	private List<Book> list;

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "BookList [list=" + list + "]";
	}


	
}
