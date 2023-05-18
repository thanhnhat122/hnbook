package hcmute.edu.hnlbook.repository;

import hcmute.edu.hnlbook.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  Order findByEmailAndStatus(String email, Order.StatusEnum chua_dat_hang);
  List<Order> findByEmail(String email);
}
