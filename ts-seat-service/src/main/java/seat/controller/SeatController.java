package seat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import seat.domain.SeatRequest;
import seat.domain.Ticket;
import seat.service.SeatService;

@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;

    @RequestMapping(path = "/welcome", method = RequestMethod.GET)
    public String home() {
        return "Welcome Welcometo [ Seat Service ] !";
    }

    // assign seats
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/seat/getSeat", method= RequestMethod.POST)
    public Ticket create(@RequestBody SeatRequest seatRequest,@RequestHeader HttpHeaders headers){
        return seatService.distributeSeat(seatRequest,headers);
    }

    // search the rest of tickets in the interval
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/seat/getLeftTicketOfInterval", method= RequestMethod.POST)
    public int getLeftTicketOfInterval(@RequestBody SeatRequest seatRequest,@RequestHeader HttpHeaders headers){
        return seatService.getLeftTicketOfInterval(seatRequest,headers);
    }
}
