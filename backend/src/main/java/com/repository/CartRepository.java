package com.repository;

import com.model.Cart;
import com.model.Product;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>  {

    @Query("select c from Cart c where c.user = :userId and c.status = :status")
    List<Cart> findByUserIdAndStatus(@Param("userId") User user, @Param("status") String status);
}
