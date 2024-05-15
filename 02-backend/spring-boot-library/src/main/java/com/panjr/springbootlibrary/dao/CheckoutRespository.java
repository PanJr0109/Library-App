package com.panjr.springbootlibrary.dao;


import com.panjr.springbootlibrary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CheckoutRespository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

    List<Checkout> findBooksByUserEmail(String userEmail);


    @Modifying
    @Query("delete from Checkout c where c.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);


}
