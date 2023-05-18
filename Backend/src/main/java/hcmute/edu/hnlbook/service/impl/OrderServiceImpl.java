package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.dto.OrderDTO;
import hcmute.edu.hnlbook.model.Book;
import hcmute.edu.hnlbook.model.Order;
import hcmute.edu.hnlbook.model.OrderItem;
import hcmute.edu.hnlbook.repository.BookRepository;
import hcmute.edu.hnlbook.repository.OrderItemRepository;
import hcmute.edu.hnlbook.repository.OrderRepository;
import hcmute.edu.hnlbook.repository.UserRepository;
import hcmute.edu.hnlbook.service.OrderItemService;
import hcmute.edu.hnlbook.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hcmute.edu.hnlbook.model.Order.StatusEnum.*;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemService orderItemService;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Override
  public List<Order> getAllOrder() {
    return orderRepository.findAll();
  }

  @Override
  public Optional<Order> findById(Integer id) {
    return orderRepository.findById(id);
  }

  @Override
  public Order insertOrder(Order newOrder) {
    newOrder.setStatus(Order.StatusEnum.Cho_Xac_Nhan);
    return orderRepository.save(newOrder);
  }

  @Override
  public Order updateOrder(Order newOrder, Integer id) {
    return orderRepository.findById(id)
        .map(order -> {
          order.setEmail(newOrder.getEmail());
          order.setStatus(newOrder.getStatus());
          return orderRepository.save(order);
        }).orElseGet(() -> {
          newOrder.setStatus(Order.StatusEnum.Cho_Xac_Nhan);
          return orderRepository.save(newOrder);
        });
  }

  @Override
  public boolean existById(Integer id) {
    return orderRepository.existsById(id);
  }

  @Override
  public void deleteOrder(Integer id) {
    orderRepository.deleteById(id);
  }

  @Override
  public Order getOrder(String email) {
    return orderRepository.findByEmailAndStatus(email, Chua_Dat_Hang);
  }

  @Override
  public OrderDTO getOrderDTO(String email) {
    Order order = orderRepository.findByEmailAndStatus(email, Chua_Dat_Hang);
    OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
    orderDTO.setTotalPrice(getTotalPrice(orderDTO.getId()));
    orderDTO.setUser(userRepository.findByEmail(orderDTO.getEmail()));
    return orderDTO;
  }

  @Override
  public Optional<Order> orderOrder(Integer id) {
    return orderRepository.findById(id)
        .map(order -> {
          List<OrderItem> orderItems = orderItemService.getAllOrderItemByOrderId(order.getID());
          for (OrderItem orderItem : orderItems) {
            bookRepository.findById(orderItem.getBookID())
                .map(book -> {
                  book.setRemain(book.getRemain() - orderItem.getQuantity());
                  book.setSell(book.getSell() + orderItem.getQuantity());
                  bookRepository.save(book);
                  return book;
                });
          }
          order.setStatus(Cho_Xac_Nhan);
          return orderRepository.save(order);
        });
  }

  @Override
  public List<Order> getAllOrderForStaff() {
    List<Order> orderList = orderRepository.findAll();
    List<Order> orderListForStaff = new ArrayList<Order>();
    for (Order order: orderList
         ) {
      if(order.getStatus() != Chua_Dat_Hang){
        orderListForStaff.add(order);
      }
    }
    return orderListForStaff;
  }

  @Override
  public List<Order> findByEmail(String email) {
    return orderRepository.findByEmail(email);
  }

  @Override
  public double getTotalPrice(Integer id) {
    List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(id);
    double totalPrice = 0;
    for (OrderItem orderItem : orderItems) {
      int bookID = orderItem.getBookID();
      Optional<Book> book = bookRepository.findById(bookID);
      if (book.isPresent()) {
        double priceOfOneItem = orderItem.getQuantity() * book.get().getPrice();
        totalPrice = totalPrice + priceOfOneItem;
      }
    }

    return totalPrice;
  }

  @Override
  public OrderDTO getOrderDTOById(Integer id) {
    Optional<Order> order = orderRepository.findById(id);
    OrderDTO orderDTO = modelMapper.map(order, (Type) OrderDTO.class);
    orderDTO.setTotalPrice(getTotalPrice(orderDTO.getId()));
    return orderDTO;
  }

  @Override
  public List<OrderDTO> getOrderDTOByEmail(String email, Integer statusNumber) {
    List<OrderDTO> orderDTOList =
        orderRepository.findByEmail(email).stream()
            .map(order -> modelMapper.map(order, OrderDTO.class))
            .collect(Collectors.toList());
    for (OrderDTO orderDTO : orderDTOList) {
      orderDTO.setTotalPrice(getTotalPrice(orderDTO.getId()));
    }
    removeChuaDatHang(orderDTOList);
    filterByStatusNumber(orderDTOList, statusNumber);
    return orderDTOList;
    }

  @Override
  public Optional<Order> confirmOrder(Integer id) {
    return orderRepository.findById(id)
        .map(order -> {
          order.setStatus(Dang_Giao);
          return orderRepository.save(order);
        });
  }

  @Override
  public Optional<Order> receivedOrder(Integer id) {
    return orderRepository.findById(id)
        .map(order -> {
          order.setStatus(Da_Nhan);
          return orderRepository.save(order);
        });
  }

  @Override
  public Optional<Order> cancelledOrder(Integer id) {
    return orderRepository.findById(id)
        .map(order -> {
          order.setStatus(Da_Huy);
          return orderRepository.save(order);
        });
  }

  @Override
  public List<OrderDTO> getListOrderDTO() {
    List<OrderDTO> orderDTOList =
        orderRepository.findAll().stream()
            .map(order -> modelMapper.map(order, OrderDTO.class))
            .collect(Collectors.toList());
    for (OrderDTO orderDTO : orderDTOList) {
      orderDTO.setTotalPrice(getTotalPrice(orderDTO.getId()));
      orderDTO.setUser(userRepository.findByEmail(orderDTO.getEmail()));
    }
    removeChuaDatHang(orderDTOList);
    return orderDTOList;

  }

  private void filterByStatusNumber(List<OrderDTO> orderDTOList, Integer statusNumber) {
    switch (statusNumber) {
      case 0: return;
      case 1: { // Lấy order đang trạng thái Cho_Xac_Nhan
        orderDTOList.removeIf(order -> !order.getStatus().toString().equals(OrderDTO.StatusEnum.Cho_Xac_Nhan.toString()));
      }; break;
      case 2: { // Lấy order đang trạng thái Dang_Giao
        orderDTOList.removeIf(order -> !order.getStatus().toString().equals(OrderDTO.StatusEnum.Dang_Giao.toString()));
      } break;
      case 3: { // Lấy order đang trạng thái Da_Nhan
        orderDTOList.removeIf(order -> !order.getStatus().toString().equals(OrderDTO.StatusEnum.Da_Nhan.toString()));
      } break;
      case 4: { // Lấy order đang trạng thái Da_Huy
        orderDTOList.removeIf(order -> !order.getStatus().toString().equals(OrderDTO.StatusEnum.Da_Huy.toString()));
      } break;
    }
  }

  private void removeChuaDatHang(List<OrderDTO> orderDTOList) {
    orderDTOList.removeIf(order -> order.getStatus().toString().equals(OrderDTO.StatusEnum.Chua_Dat_Hang.toString()));
  }
}
