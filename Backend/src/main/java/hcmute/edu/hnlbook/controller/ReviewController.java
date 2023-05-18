package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.dto.ReviewDTO;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.model.Publisher;
import hcmute.edu.hnlbook.model.Review;
import hcmute.edu.hnlbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Review")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping("")
  DataResponse getAllReview() {
    List<Review> listReview = reviewService.getAll();
    return new DataResponse(listReview);
  }

  @GetMapping("/{id}")
  DataResponse findReviewById(@PathVariable Integer id) {
    Optional<Review> foundReview = reviewService.findById(id);
    if (foundReview.isPresent())
      return new DataResponse(foundReview);
    else
      throw new RuntimeException("Cannot find review with id = " + id);
  }

  @GetMapping("/details/{id}")
  DataResponse findReviewDTOById(@PathVariable Integer id) {
    return new DataResponse(reviewService.findReviewDTOById(id));
  }

  @GetMapping("/findAll")
  DataResponse getAllReviewDTO() {
    return new DataResponse(reviewService.getAllReviewDTO());
  }

  @GetMapping("/findByBookId/{id}")
  DataResponse findReviewByBookId(@PathVariable Integer id) {
    List<ReviewDTO> reviewDTOs = reviewService.getReviewDTOByBookId(id);
    if (reviewDTOs.size() > 0) {
      return new DataResponse(reviewDTOs);
    }
    throw new RuntimeException("Cannot find review with book id = " + id);
  }

  @GetMapping("/findByOrderItemId/{id}")
  DataResponse findReviewByOrderItemId(@PathVariable Integer id) {
    Review review = reviewService.getReviewByOrderItemId(id);
    if (review != null) {
      return new DataResponse(review);
    }
    throw new RuntimeException("Cannot find review with order item id = " + id);
  }

  @PostMapping("")
  DataResponse insertReview(@RequestBody @Validated Review newReview, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(reviewService.insert(newReview));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PutMapping("")
  DataResponse updateReview(@RequestBody @Validated Review newReview, BindingResult result, @RequestParam Integer id) {
    if (!result.hasErrors()){
      Review updateReview = reviewService.update(newReview, id);
      return new DataResponse(updateReview);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PutMapping("/updateResponse")
  DataResponse updateReviewResponse(@RequestBody @Validated Review newReview, BindingResult result) {
    if (!result.hasErrors()){
      Review updateReview = reviewService.updateReviewResponse(newReview);
      return new DataResponse(updateReview);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("")
  DataResponse deleteReview(@RequestParam Integer id) {
    boolean exists = reviewService.existsById(id);
    if (exists) {
      reviewService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find review with id = " + id + " to delete");
  }
}
