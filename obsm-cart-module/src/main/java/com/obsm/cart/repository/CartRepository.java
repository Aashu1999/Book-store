package com.obsm.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.obsm.cart.model.CustomerCart;

@Repository
public interface CartRepository extends JpaRepository<CustomerCart, Long>{
	@Query("SELECT C FROM CustomerCart C WHERE  C.customerId=:customerId")
    public CustomerCart findByCustomerId(long customerId);

}
