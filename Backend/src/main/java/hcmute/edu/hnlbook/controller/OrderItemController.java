package hcmute.edu.hnlbook.controller;

import hcmute.edu.hnlbook.dto.OrderItemDTO;
import hcmute.edu.hnlbook.model.DataResponse;
import hcmute.edu.hnlbook.model.Order;
import hcmute.edu.hnlbook.model.OrderItem;
import hcmute.edu.hnlbook.service.OrderItemService;
import hcmute.edu.hnlbook.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/OrderItem")
public class OrderItemController {

  @Autowired
  private OrderItemService orderItemService;

  @GetMapping("")
  DataResponse getAllOrderItem(){
    List<OrderItem> orderItemList = orderItemService.getAllOrderItem();
    if (orderItemList.size() > 0) {
      return new DataResponse(orderItemList);
    }
    throw new RuntimeException("No OrderItem found");
  }
  @GetMapping("/{id}")
  DataResponse findById(@PathVariable Integer id) {
    Optional<OrderItem> foundOrderItem = orderItemService.findById(id);
    if(foundOrderItem.isPresent()) {
      return new DataResponse(foundOrderItem);
    }
    throw new RuntimeException("Cannot find OrderItem with id = " + id);
  }
  @PostMapping("")
  DataResponse insertOrderItem(@RequestBody @Validated OrderItem newOrderItem, BindingResult result){
    if(!result.hasErrors()){
      return new DataResponse(orderItemService.insertOrderItem(newOrderItem));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateOrderItem(@RequestBody @Validated OrderItem newOrderItem, BindingResult result, @PathVariable Integer id){
    if(!result.hasErrors()) {
      OrderItem updateOrderItem = orderItemService.updateOrderItem(newOrderItem,id);
      return new DataResponse(updateOrderItem);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("/{id}")
  DataResponse deleteOrderItem(@PathVariable Integer id){
    if(orderItemService.existById(id)){
      orderItemService.deleteOrderItem(id);
      return new DataResponse("");
    }
    else {
      throw new RuntimeException("Cannot find OrderItem with id ="+ id+" to delete");
    }

  }
  @PostMapping("/orderId")
  DataResponse getAllOrderItemDTOByOrderId(@RequestParam Integer orderId) {
    List<OrderItemDTO> orderItemDTOs = orderItemService.getAllOrderItemDTOByOrderId(orderId);
    if (orderItemDTOs.size() > 0) {
      return new DataResponse(orderItemDTOs);
    }
    throw new RuntimeException("No OrderItem found");
  }

  @PostMapping("/findExistBookInOrder")
  DataResponse findExistBookInOrder(@RequestParam int orderId, @RequestParam int bookId){
    OrderItem orderItem = orderItemService.findExistBookInOrder(orderId, bookId);
    return new DataResponse(orderItem);
  }

  @PostMapping("/validQuantity")
  DataResponse checkValidQuantity(@RequestParam Integer orderItemId, @RequestParam Integer bookId, @RequestParam Integer quantity) {
    Integer result = orderItemService.checkValidQuantity(bookId, quantity);
    if (result == -1) {
      Optional<OrderItem> orderItem = orderItemService.updateOrderItemQuantity(orderItemId, quantity);
      return new DataResponse(orderItem);
    } else if (result == 0) {
      throw new RuntimeException("Quantity < 0");
    }
    orderItemService.updateOrderItemQuantity(orderItemId, result);
    throw new RuntimeException("Remain: " + result);
  }
}
