package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.dto.ReviewDTO;
import hcmute.edu.hnlbook.model.Review;
import hcmute.edu.hnlbook.repository.ReviewRepository;
import hcmute.edu.hnlbook.repository.UserRepository;
import hcmute.edu.hnlbook.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private UserRepository userRepository;

  public ReviewDTO reviewToReviewDTO(Review review) {
    ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
    reviewDTO.setUser(userRepository.findById(reviewDTO.getUserEmail()));
    return reviewDTO;
  }

  @Override
  public List<Review> getAll() {
    return reviewRepository.findAll();
  }

  @Override
  public Optional<Review> findById(int id) {
    return reviewRepository.findById(id);
  }

  @Override
  public ReviewDTO findReviewDTOById(int id) {
    Optional<Review> review = reviewRepository.findById(id);
    ReviewDTO reviewDTO = new ReviewDTO();
    if (review.isPresent()) {
      reviewDTO = reviewToReviewDTO(review.get());
    }
    return reviewDTO;
  }



  @Override
  public List<ReviewDTO> getAllReviewDTO() {
    List<Review> reviews = reviewRepository.findAll();
    List<ReviewDTO> reviewDTOs = new ArrayList<>();
    for(Review review: reviews) {
      reviewDTOs.add(reviewToReviewDTO(review));
    }
    return reviewDTOs;
  }

  @Override
  public List<ReviewDTO> getReviewDTOByBookId(int id) {
    List<Review> reviews = reviewRepository.findByBookId(id);
    List<ReviewDTO> reviewDTOs = new ArrayList<>();
    for(Review review: reviews) {
      reviewDTOs.add(reviewToReviewDTO(review));
    }
    return reviewDTOs;
  }

  @Override
  public Review getReviewByOrderItemId(int id) {
    return reviewRepository.findByOrderItemId(id);
  }

  @Override
  public Review insert(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public Review update(Review newReview, int id) {
    return reviewRepository
        .findById(id)
        .map(
            review -> {
              review.setBookId(newReview.getBookId());
              review.setUserEmail(newReview.getUserEmail());
              review.setOrderItemId(newReview.getOrderItemId());
              review.setComment(newReview.getComment());
              review.setRate(newReview.getRate());
              review.setResponse(newReview.getResponse());
              return reviewRepository.save(review);
            })
        .orElseGet(
            () -> reviewRepository.save(newReview));
  }

  @Override
  public Review updateReviewResponse(Review newReview) {
    return reviewRepository.findById(newReview.getId())
        .map(
            review -> {
              review.setResponse(newReview.getResponse());
              return reviewRepository.save(review);
            })
        .orElseGet(
            () -> null);
  }

  @Override
  public boolean existsById(int id) {
    return reviewRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    reviewRepository.deleteById(id);
  }
}
