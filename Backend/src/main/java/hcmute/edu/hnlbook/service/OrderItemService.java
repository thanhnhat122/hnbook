package hcmute.edu.hnlbook.service;

import hcmute.edu.hnlbook.dto.OrderItemDTO;
import hcmute.edu.hnlbook.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
  List<OrderItem> getAllOrderItem();

  Optional<OrderItem> findById(Integer id);

  Object insertOrderItem(OrderItem newOrderItem);

  OrderItem updateOrderItem(OrderItem newOrderItem, Integer id);

  boolean existById(Integer id);

  void deleteOrderItem(Integer id);

  List<OrderItemDTO> getAllOrderItemDTOByOrderId(Integer orderId);

  Integer checkValidQuantity(Integer bookId, Integer quantity);

  Optional<OrderItem> updateOrderItemQuantity(Integer orderItemId, Integer quantity);

  List<OrderItem> getAllOrderItemByOrderId(Integer orderId);

  OrderItem findExistBookInOrder(int orderId, int bookId);
}
