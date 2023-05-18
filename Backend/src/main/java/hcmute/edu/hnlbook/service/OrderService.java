package hcmute.edu.hnlbook.service;

import hcmute.edu.hnlbook.dto.OrderDTO;
import hcmute.edu.hnlbook.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
  List<Order> getAllOrder();

  Optional<Order> findById(Integer id);

  Order insertOrder(Order newOrder);

  Order updateOrder(Order newOrder, Integer id);

  boolean existById(Integer id);

  void deleteOrder(Integer id);

  Order getOrder(String email);

  OrderDTO getOrderDTO(String email);

  Optional<Order> orderOrder(Integer id);

  List<Order> getAllOrderForStaff();

  List<Order> findByEmail(String email);

  double getTotalPrice(Integer id);

  OrderDTO getOrderDTOById(Integer id);

  List<OrderDTO> getOrderDTOByEmail(String email, Integer statusNumber);

  Optional<Order> confirmOrder(Integer id);

  Optional<Order> receivedOrder(Integer id);

  Optional<Order> cancelledOrder(Integer id);

  List<OrderDTO> getListOrderDTO();
}
