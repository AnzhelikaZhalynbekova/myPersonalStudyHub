package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review createReview(Review review);
    Optional<Review> getReviewById(Long id);
    List<Review> getAllReviews();
    Review updateReview(Long id, Review review);
    boolean deleteReview(Long id);
}
