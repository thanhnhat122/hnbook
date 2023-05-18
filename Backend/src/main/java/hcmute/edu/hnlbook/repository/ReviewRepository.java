package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
  List<Review> findByBookId(int id);

  Review findByOrderItemId(int id);
}
