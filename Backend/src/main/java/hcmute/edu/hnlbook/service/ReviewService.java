package hcmute.edu.hnlbook.service;

import hcmute.edu.hnlbook.dto.ReviewDTO;
import hcmute.edu.hnlbook.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  List<Review> getAll();

  Optional<Review> findById(int id);

  ReviewDTO findReviewDTOById(int id);

  List<ReviewDTO> getAllReviewDTO();

  List<ReviewDTO> getReviewDTOByBookId(int id);

  Review getReviewByOrderItemId(int id);

  Review insert(Review review);

  Review update(Review review, int id);

  Review updateReviewResponse(Review newReview);

  boolean existsById(int id);

  void deleteById(int id);
}
