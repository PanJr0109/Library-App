package com.panjr.springbootlibrary.service;


import com.panjr.springbootlibrary.dao.BookRepository;
import com.panjr.springbootlibrary.dao.ReviewRespository;
import com.panjr.springbootlibrary.entity.Review;
import com.panjr.springbootlibrary.requestmodels.ReviewRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {
    private ReviewRespository reviewRespository;


    @Autowired
    public ReviewService(ReviewRespository reviewRespository){
        this.reviewRespository = reviewRespository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception{
        Review validateReview = reviewRespository.findByUserEmailAndBookId(userEmail,reviewRequest.getBookId());

        if(validateReview!=null){
            throw new Exception("Review already created");
        }
        Review review= new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if(reviewRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object:: toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRespository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long bookId){
        Review validateReview = reviewRespository.findByUserEmailAndBookId(userEmail,bookId);
        if(validateReview!=null){
            return true;
        }
        else {
            return false;
        }
    }
}
