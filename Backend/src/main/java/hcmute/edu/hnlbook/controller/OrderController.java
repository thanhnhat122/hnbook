package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.dto.OrderDTO;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.model.Order;
import hcmute.edu.hnlbook.service.OrderService;
import hcmute.edu.hnlbook.utlis.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/Order")
public class  OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping("")
  DataResponse getAllOrder(){
    List<Order> orderList = orderService.getAllOrder();
    List<Order> orderListForStaff = new ArrayList<Order>();
    if (orderList.size() > 0) {
      return new DataResponse(orderList);
    }
    throw new RuntimeException("No Order found");
  }
  @GetMapping("/forStaff")
  DataResponse getOrderForStaff(){
    List<Order> orderList = orderService.getAllOrderForStaff();
    if (orderList.size() > 0) {
      return new DataResponse(orderList);
    }
    throw new RuntimeException("No Order found");
  }
  @PostMapping("/email")
  DataResponse getOrderChuaDatHang(@RequestParam String email){
    if(Validate.isWhatever(Validate.Type.EMAIl, email))
    {
      Order order = orderService.getOrder(email);
      return new DataResponse(order);
    }
    throw new RuntimeException("Incorrect email format");
  }

  @PostMapping("/cart/email")
  DataResponse getOrderDTOChuaDatHang(@RequestParam String email){
    if(Validate.isWhatever(Validate.Type.EMAIl, email))
    {
      OrderDTO orderDTO = orderService.getOrderDTO(email);
      return new DataResponse(orderDTO);
    }
    throw new RuntimeException("Incorrect email format");
  }

  @GetMapping("/{id}")
  DataResponse findById(@PathVariable Integer id) {
    Optional<Order> foundOrder = orderService.findById(id);
    if(foundOrder.isPresent()) {
      return new DataResponse(foundOrder);
    }
    throw new RuntimeException("Cannot find Order with id = " + id);
  }

  @GetMapping("/order/{id}")
  DataResponse orderOrder(@PathVariable Integer id) {
    Optional<Order> foundOrder = orderService.orderOrder(id);
    if(foundOrder.isPresent()) {
      return new DataResponse(foundOrder);
    }
    throw new RuntimeException("Cannot find Order with id = " + id);
  }

  @GetMapping("/confirm/{id}")
  DataResponse confirmOrder(@PathVariable Integer id) {
    Optional<Order> foundOrder = orderService.confirmOrder(id);
    if(foundOrder.isPresent()) {
      return new DataResponse(foundOrder);
    }
    throw new RuntimeException("Cannot find Order with id = " + id);
  }
  @GetMapping("/received/{id}")
  DataResponse receivedOrder(@PathVariable Integer id) {
    Optional<Order> foundOrder = orderService.receivedOrder(id);
    if(foundOrder.isPresent()) {
      return new DataResponse(foundOrder);
    }
    throw new RuntimeException("Cannot find Order with id = " + id);
  }
  @GetMapping("/cancelled/{id}")
  DataResponse cancelledOrder(@PathVariable Integer id) {
    Optional<Order> foundOrder = orderService.cancelledOrder(id);
    if(foundOrder.isPresent()) {
      return new DataResponse(foundOrder);
    }
    throw new RuntimeException("Cannot find Order with id = " + id);
  }
  @PostMapping("")
  DataResponse insertOrder(@RequestBody @Validated Order newOrder, BindingResult result){
    if(!result.hasErrors()){
      return new DataResponse(orderService.insertOrder(newOrder));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateOrder(@RequestBody @Validated Order newOrder, BindingResult result, @PathVariable Integer id){
    if(!result.hasErrors()) {
      Order updateOrder = orderService.updateOrder(newOrder,id);
      return new DataResponse(updateOrder);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("/{id}")
  DataResponse deleteOrder(@PathVariable Integer id){
    if(orderService.existById(id)){
      orderService.deleteOrder(id);
      return new DataResponse("");
    }
    else {
      throw new RuntimeException("Cannot find Order with id ="+ id+" to delete");
    }
  }

  @GetMapping("/findEmail/{email}")
  DataResponse findByEmail(@PathVariable String email) {
    List<Order> orderList = orderService.findByEmail(email);
    if(orderList.size() > 0) {
      return new DataResponse(orderList);
    }
    throw new RuntimeException("Cannot find Order with email = " + email);
  }

  @GetMapping("/totalPrice/{orderId}")
  DataResponse getTotalPrice(@PathVariable Integer orderId) {
    double totalPrice = orderService.getTotalPrice(orderId);
    if(totalPrice > 0)
      return new DataResponse(totalPrice);
    throw new RuntimeException("Something went wrong when loading price");
  }

  @PostMapping("orderDTO")
  DataResponse getOrderDTO(@RequestParam String email, @RequestParam Integer statusNumber){
    if(Validate.isWhatever(Validate.Type.EMAIl, email)) {
      List<OrderDTO> orderDTO = orderService.getOrderDTOByEmail(email, statusNumber);
      return new DataResponse(orderDTO);
    }
    throw new RuntimeException("Incorrect email format");
  }
  @GetMapping("orderDTOList")
  DataResponse getListOrderDTO(){
    List<OrderDTO> orderDTOList = orderService.getListOrderDTO();
    return new DataResponse(orderDTOList);

  }


}
