package com.panjr.springbootlibrary.service;


import com.panjr.springbootlibrary.dao.BookRepository;
import com.panjr.springbootlibrary.dao.CheckoutRespository;
import com.panjr.springbootlibrary.entity.Book;
import com.panjr.springbootlibrary.entity.Checkout;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;

    private CheckoutRespository checkoutRespository;

    public  BookService (BookRepository bookRepository, CheckoutRespository checkoutRespository){
        this.bookRepository = bookRepository;
        this.checkoutRespository =checkoutRespository;
    }

    public Book checkoutBook(String userEmail, Long bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);
        Checkout validateCheckout= checkoutRespository.findByUserEmailAndBookId(userEmail,bookId);


        if(!book.isPresent() || validateCheckout!= null || book.get().getCopiesAvailable() <=0){
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() -1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );
        checkoutRespository.save(checkout);
        return book.get();
    }

    public Boolean checkoutBookByUser(String userEmail, Long bookId){
        Checkout validateCheckout =  checkoutRespository.findByUserEmailAndBookId(userEmail,bookId);
        if(validateCheckout!= null){
            return true;
        }
        else {
            return false;
        }
    }

    public int currentLoansCount(String userEmail){
        return checkoutRespository.findBooksByUserEmail(userEmail).size();
    }
}
