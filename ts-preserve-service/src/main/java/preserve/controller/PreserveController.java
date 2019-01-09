package preserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import preserve.domain.*;
import preserve.service.PreserveService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class PreserveController {

    @Autowired
    private PreserveService preserveService;

    @RequestMapping(path = "/welcome", method = RequestMethod.GET)
    public String home() {
        Set<Order> orders = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            Order order = new Order();
            order.setAccountId(new UUID(16, 16));
            order.setBoughtDate(new Date());
            order.setCoachNumber(i);
            order.setContactsDocumentNumber("test");
            order.setContactsName("test");
            orders.add(order);
        }

        return "Welcome to [ Preserve Service ] !";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/preserve", method = RequestMethod.POST)
    public OrderTicketsResult preserve(@RequestBody OrderTicketsInfo oti,@CookieValue String loginId,
                                       @CookieValue String loginToken, @RequestHeader HttpHeaders headers){
        System.out.println("[Preserve Service][Preserve] Account " + loginId + " order from " +
            oti.getFrom() + " -----> " + oti.getTo() + " at " + oti.getDate());
        return preserveService.preserve(oti,loginId,loginToken, headers);
    }

}
