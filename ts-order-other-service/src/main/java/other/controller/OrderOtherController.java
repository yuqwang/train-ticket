package other.controller;

import edu.fudan.common.entity.*;
import edu.fudan.common.util.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


import other.entity.Order;
import other.entity.QueryInfo;
import other.service.OrderOtherService;

import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/orderOtherService")
public class OrderOtherController {

    @Autowired
    private OrderOtherService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderOtherController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Order Other Service ] !";
    }

    /***************************For Normal Use***************************/

    @PostMapping(value = "/orderOther/tickets")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seatRequest", value = "Seat",dataType = "Seat", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LeftTicketInfo.class)
    })
    public HttpEntity getTicketListByDateAndTripId(@RequestBody Seat seatRequest, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[getSoldTickets][Get Sold Ticket][Travel Date: {}]", seatRequest.getTravelDate().toString());
        return ok(orderService.getSoldTickets(seatRequest, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/orderOther")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrder", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class)
    })
    public HttpEntity createNewOrder(@RequestBody Order createOrder, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[create][Create Order][from {} to {} at {}]", createOrder.getFrom(), createOrder.getTo(), createOrder.getTravelDate());
        return ok(orderService.create(createOrder, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/orderOther/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrder", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class)
    })
    public HttpEntity addcreateNewOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[addNewOrder][Add new order][OrderId: {}]", order.getId());
        return ok(orderService.addNewOrder(order, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/orderOther/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qi", value = "QueryInfo",dataType = "QueryInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity queryOrders(@RequestBody QueryInfo qi,
                                  @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[queryOrders][Query Orders][for LoginId :{}]", qi.getLoginId());
        return ok(orderService.queryOrders(qi, qi.getLoginId(), headers));

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/orderOther/refresh")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qi", value = "QueryInfo",dataType = "QueryInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity queryOrdersForRefresh(@RequestBody QueryInfo qi,
                                            @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[queryOrdersForRefresh][Query Orders][for LoginId:{}]", qi.getLoginId());
        return ok(orderService.queryOrdersForRefresh(qi, qi.getLoginId(), headers));
    }


    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/{travelDate}/{trainNumber}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "travelDate", value = "travelDate",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "trainNumber", value = "trainNumber",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = SoldTicket.class)
    })
    public HttpEntity calculateSoldTicket(@PathVariable String travelDate, @PathVariable String trainNumber,
                                          @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[queryAlreadySoldOrders][Calculate Sold Tickets][Date: {} TrainNumber: {}]", travelDate, trainNumber);
        return ok(orderService.queryAlreadySoldOrders(StringUtils.String2Date(travelDate), trainNumber, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/price/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity getOrderPrice(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[getOrderPrice][Get Order Price][Order Id: {}]", orderId);
        return ok(orderService.getOrderPrice(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/orderPay/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity payOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[payOrder][Pay Order][Order Id: {}]", orderId);
        return ok(orderService.payOrder(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity getOrderById(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[getOrderById][Get Order By Id][Order Id: {}]", orderId);
        return ok(orderService.getOrderById(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/status/{orderId}/{status}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "status", value = "status",dataType = "int", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity modifyOrder(@PathVariable String orderId, @PathVariable int status, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[modifyOrder][Modify Order Status][Order Id: {}]", orderId);
        return ok(orderService.modifyOrder(orderId, status, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther/security/{checkDate}/{accountId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkDate", value = "checkDate",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "accountId", value = "accountId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = OrderSecurity.class)
    })
    public HttpEntity securityInfoCheck(@PathVariable String checkDate, @PathVariable String accountId,
                                        @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[checkSecurityAboutOrder][Security Info Get][CheckDate:{} , AccountId:{}]",checkDate,accountId);
        return ok(orderService.checkSecurityAboutOrder(StringUtils.String2Date(checkDate), accountId, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/orderOther")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderInfo", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity saveOrderInfo(@RequestBody Order orderInfo,
                                    @RequestHeader HttpHeaders headers) {

        OrderOtherController.LOGGER.info("[saveChanges][Save Order Info][OrderId:{}]",orderInfo.getId());
        return ok(orderService.saveChanges(orderInfo, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/orderOther/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class)
    })
    public HttpEntity updateOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[updateOrder][Update Order][OrderId: {}]", order.getId());
        return ok(orderService.updateOrder(order, headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/orderOther/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity deleteOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[deleteOrder][Delete Order][OrderId: {}]", orderId);
        return ok(orderService.deleteOrder(orderId, headers));
    }

    /***************For super admin(Single Service Test*******************/

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/orderOther")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Order.class,responseContainer = "ArrayList")
    })
    public HttpEntity findAllOrder(@RequestHeader HttpHeaders headers) {
        OrderOtherController.LOGGER.info("[getAllOrders][Find All Order]");
        return ok(orderService.getAllOrders(headers));
    }

}
