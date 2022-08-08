package preserve.controller;

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
import edu.fudan.common.entity.*;
import preserve.service.PreserveService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/preserveservice")
public class PreserveController {

    @Autowired
    private PreserveService preserveService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PreserveController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Preserve Service ] !";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/preserve")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oti", value = "OrderTicketsInfo",dataType = "OrderTicketsInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Success.",response = Order.class)
    })
    public HttpEntity preserve(@RequestBody OrderTicketsInfo oti,
                               @RequestHeader HttpHeaders headers) {
        PreserveController.LOGGER.info("[preserve][Preserve Account order][from {} to {} at {}]", oti.getFrom(), oti.getTo(), oti.getDate());
        return ok(preserveService.preserve(oti, headers));
    }

}
