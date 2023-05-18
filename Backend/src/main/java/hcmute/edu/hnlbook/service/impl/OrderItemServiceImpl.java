package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.dto.OrderItemDTO;
import hcmute.edu.hnlbook.model.Book;
import hcmute.edu.hnlbook.model.OrderItem;
import hcmute.edu.hnlbook.repository.BookRepository;
import hcmute.edu.hnlbook.repository.OrderItemRepository;
import hcmute.edu.hnlbook.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;
  @Override
  public List<OrderItem> getAllOrderItem() {
    return orderItemRepository.findAll();
  }

  @Override
  public Optional<OrderItem> findById(Integer id) {
    return orderItemRepository.findById(id);
  }

  @Override
  public Object insertOrderItem(OrderItem newOrderItem) {
    return orderItemRepository.save(newOrderItem);
  }

  @Override
  public OrderItem updateOrderItem(OrderItem newOrderItem, Integer id) {
    return orderItemRepository.findById(id)
        .map(orderItem -> {
          orderItem.setOrderID(newOrderItem.getOrderID());
          orderItem.setBookID(newOrderItem.getBookID());
          orderItem.setQuantity(newOrderItem.getQuantity());
          return orderItemRepository.save(orderItem);
        }).orElseGet(() ->{
          return orderItemRepository.save(newOrderItem);
        });
  }

  @Override
  public boolean existById(Integer id) {
    return orderItemRepository.existsById(id);
  }

  @Override
  public void deleteOrderItem(Integer id) {
    orderItemRepository.deleteById(id);
  }

  @Override
  public List<OrderItemDTO> getAllOrderItemDTOByOrderId(Integer orderId) {
    List<OrderItemDTO> orderItemDTOList = orderItemRepository.findAllByOrderId(orderId).stream().map(
    orderItem ->modelMapper.map(orderItem, OrderItemDTO.class))
        .collect(Collectors.toList());
    for(OrderItemDTO orderItemDTO: orderItemDTOList) {
      orderItemDTO.setBook(bookRepository.findById(orderItemDTO.getBookId())
          .map(bookTemp -> {
            bookTemp.setImage(bookTemp.getImage().split(", ")[0]);
            return bookTemp;
          }));
      orderItemDTO.setTotalPrice(orderItemDTO.getBook().get().getPrice() * orderItemDTO.getQuantity());
    }
    return orderItemDTOList;
  }

  @Override
  public List<OrderItem> getAllOrderItemByOrderId(Integer orderId) {
    return orderItemRepository.findAllByOrderId(orderId);
  }

  @Override
  public OrderItem findExistBookInOrder(int orderId, int bookId) {
    return orderItemRepository.findByOrderIdAndBookId(orderId, bookId);
  }

  @Override
  public Integer checkValidQuantity(Integer bookId, Integer quantity) {
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent()) {
      if (book.get().getRemain() < quantity) {
        return book.get().getRemain();
      } else if (quantity < 1) {
        return 0;
      }
    }

    return -1;
  }

  @Override
  public Optional<OrderItem> updateOrderItemQuantity(Integer orderItemId, Integer quantity) {
    return orderItemRepository.findById(orderItemId).map(
        orderItem -> {
          orderItem.setQuantity(quantity);
          return orderItemRepository.save(orderItem);
        }
    );
  }
}
