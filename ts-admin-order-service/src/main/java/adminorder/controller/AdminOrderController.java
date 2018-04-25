package adminorder.controller;

import adminorder.domain.request.AddOrderRequest;
import adminorder.domain.request.DeleteOrderRequest;
import adminorder.domain.request.UpdateOrderRequest;
import adminorder.domain.response.AddOrderResult;
import adminorder.domain.response.DeleteOrderResult;
import adminorder.domain.response.GetAllOrderResult;
import adminorder.domain.response.UpdateOrderResult;
import adminorder.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminOrderController {

    @Autowired
    AdminOrderService adminOrderService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/adminorder/getCanAdminChangeOrder", method = RequestMethod.GET)
    public boolean getCanAdminChangeOrder(@RequestHeader HttpHeaders headers){
        System.out.println("getCanAdminChangeOrder获取管理员是否有权操作订单："
                + adminOrderService.getCanAdminChangeOrder());
        return adminOrderService.getCanAdminChangeOrder();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/adminorder/setCanAdminChangeOrder/{status}", method = RequestMethod.GET)
    public void setCanAdminChangeOrder(@PathVariable String status, @RequestHeader HttpHeaders headers){
        System.out.println("setCanAdminChangeOrder设置管理员是否有权操作订单：" + status);
        if(status.equals("true")){
            adminOrderService.setCanAdminChangeOrder(true);
        }else{
            adminOrderService.setCanAdminChangeOrder(false);
        }
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/adminorder/findAll/{id}", method = RequestMethod.GET)
    public GetAllOrderResult getAllOrders(@PathVariable String id, @RequestHeader HttpHeaders headers){
        return adminOrderService.getAllOrders(id, headers);
    }

    @RequestMapping(value = "/adminorder/addOrder", method= RequestMethod.POST)
    public AddOrderResult addOrder(@RequestBody AddOrderRequest request, @RequestHeader HttpHeaders headers){
        return adminOrderService.addOrder(request, headers);
    }

    @RequestMapping(value = "/adminorder/updateOrder", method= RequestMethod.POST)
    public UpdateOrderResult updateOrder(@RequestBody UpdateOrderRequest request, @RequestHeader HttpHeaders headers){
        return adminOrderService.updateOrder(request, headers);
    }

    @RequestMapping(value = "/adminorder/deleteOrder", method= RequestMethod.POST)
    public DeleteOrderResult deleteOrder(@RequestBody DeleteOrderRequest request, @RequestHeader HttpHeaders headers){
        return adminOrderService.deleteOrder(request, headers);
    }

    @RequestMapping(value = "/adminOrder/suspendOrder/{fromStationId}/{toStationId}", method = RequestMethod.GET)
    public boolean suspendOder(@PathVariable String fromStationId, @PathVariable String toStationId, @RequestHeader HttpHeaders headers){
        return adminOrderService.suspendOrder(fromStationId,toStationId,headers);
    }

    @RequestMapping(value = "/adminOrder/cancelSuspendOrder/{fromStationId}/{toStationId}", method = RequestMethod.GET)
    public boolean cancelSuspendOder(@PathVariable String fromStationId, @PathVariable String toStationId, @RequestHeader HttpHeaders headers){
        return adminOrderService.cancelSuspenOrder(fromStationId,toStationId,headers);
    }
}
