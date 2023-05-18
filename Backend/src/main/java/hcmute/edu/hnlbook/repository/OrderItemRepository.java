package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {


  List<OrderItem> findAllByOrderId(Integer orderId);

  OrderItem findByOrderIdAndBookId(int orderId, int bookId);
}
