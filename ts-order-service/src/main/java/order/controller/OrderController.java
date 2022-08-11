package order.controller;

import edu.fudan.common.entity.LeftTicketInfo;
import edu.fudan.common.entity.OrderSecurity;
import edu.fudan.common.entity.Seat;
import edu.fudan.common.entity.SoldTicket;
import edu.fudan.common.util.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import order.entity.*;
import order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/orderservice")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Order Service ] !";
    }

    /***************************For Normal Use***************************/

    @PostMapping(value = "/order/tickets")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seatRequest", value = "Seat",dataType = "Seat", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LeftTicketInfo.class)
    })
    public HttpEntity getTicketListByDateAndTripId(@RequestBody Seat seatRequest, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[getSoldTickets][Get Sold Ticket][Travel Date: {}]", seatRequest.getTravelDate().toString());
        return ok(orderService.getSoldTickets(seatRequest, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrder", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class)
    })
    public HttpEntity createNewOrder(@RequestBody Order createOrder, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[createNewOrder][Create Order][from {} to {} at {}]", createOrder.getFrom(), createOrder.getTo(), createOrder.getTravelDate());
        return ok(orderService.create(createOrder, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/order/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrder", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class)
    })
    public HttpEntity addcreateNewOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        return ok(orderService.addNewOrder(order, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/order/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qi", value = "QueryInfo",dataType = "QueryInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity queryOrders(@RequestBody OrderInfo qi,
                                  @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[queryOrders][Query Orders][for LoginId :{}]", qi.getLoginId());
        return ok(orderService.queryOrders(qi, qi.getLoginId(), headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/order/refresh")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qi", value = "OrderInfo",dataType = "OrderInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Query Orders For Refresh Success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity queryOrdersForRefresh(@RequestBody OrderInfo qi,
                                            @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[queryOrdersForRefresh][Query Orders][for LoginId:{}]", qi.getLoginId());
        return ok(orderService.queryOrdersForRefresh(qi, qi.getLoginId(), headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/{travelDate}/{trainNumber}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "travelDate", value = "travelDate",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "trainNumber", value = "trainNumber",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = SoldTicket.class)
    })
    public HttpEntity calculateSoldTicket(@PathVariable String travelDate, @PathVariable String trainNumber,
                                          @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[queryAlreadySoldOrders][Calculate Sold Tickets][Date: {} TrainNumber: {}]", travelDate, trainNumber);
        return ok(orderService.queryAlreadySoldOrders(StringUtils.String2Date(travelDate), trainNumber, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/price/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity getOrderPrice(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[getOrderPrice][Get Order Price][OrderId: {}]", orderId);
        // String
        return ok(orderService.getOrderPrice(orderId, headers));
    }


    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/orderPay/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity payOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[payOrder][Pay Order][OrderId: {}]", orderId);
        // Order
        return ok(orderService.payOrder(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity getOrderById(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[getOrderById][Get Order By Id][OrderId: {}]", orderId);
        // Order
        return ok(orderService.getOrderById(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/status/{orderId}/{status}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "status", value = "status",dataType = "int", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity modifyOrder(@PathVariable String orderId, @PathVariable int status, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[modifyOrder][Modify Order Status][OrderId: {}]", orderId);
        // Order
        return ok(orderService.modifyOrder(orderId, status, headers));
    }


    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order/security/{checkDate}/{accountId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkDate", value = "checkDate",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "accountId", value = "accountId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = OrderSecurity.class)
    })
    public HttpEntity securityInfoCheck(@PathVariable String checkDate, @PathVariable String accountId,
                                        @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[checkSecurityAboutOrder][Security Info Get][AccountId:{}]", accountId);
        return ok(orderService.checkSecurityAboutOrder(StringUtils.String2Date(checkDate), accountId, headers));
    }


    @CrossOrigin(origins = "*")
    @PutMapping(path = "/order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfo", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity saveOrderInfo(@RequestBody Order orderInfo,
                                    @RequestHeader HttpHeaders headers) {

        OrderController.LOGGER.info("[saveChanges][Save Order Info][OrderId:{}]",orderInfo.getId());
        return ok(orderService.saveChanges(orderInfo, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/order/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity updateOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        // Order
        OrderController.LOGGER.info("[updateOrder][Update Order][OrderId: {}]", order.getId());
        return ok(orderService.updateOrder(order, headers));
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/order/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity deleteOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[deleteOrder][Delete Order][OrderId: {}]", orderId);
        // Order
        return ok(orderService.deleteOrder(orderId, headers));
    }

    /***************For super admin(Single Service Test*******************/

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity findAllOrder(@RequestHeader HttpHeaders headers) {
        OrderController.LOGGER.info("[getAllOrders][Find All Order]");
        // ArrayList<Order>
        return ok(orderService.getAllOrders(headers));
    }

}
