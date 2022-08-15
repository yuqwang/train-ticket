package consignprice.controller;

import consignprice.entity.ConsignPrice;
import consignprice.service.ConsignPriceService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/consignpriceservice")
public class ConsignPriceController {

    @Autowired
    ConsignPriceService service;

    private static final Logger logger = LoggerFactory.getLogger(ConsignPriceController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ ConsignPrice Service ] !";
    }

    @GetMapping(value = "/consignprice/{weight}/{isWithinRegion}")
    public HttpEntity getPriceByWeightAndRegion(@PathVariable String weight, @PathVariable String isWithinRegion,
                                                @RequestHeader HttpHeaders headers) {
        logger.info("[getPriceByWeightAndRegion][Get price by weight and region][weight: {}, region: {}]", weight, isWithinRegion);
//        return ok(service.getPriceByWeightAndRegion(Double.parseDouble(weight),
//                Boolean.parseBoolean(isWithinRegion), headers));
        Response response = service.getPriceByWeightAndRegion(Double.parseDouble(weight),
                Boolean.parseBoolean(isWithinRegion), headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/consignprice/price")
    public HttpEntity getPriceInfo(@RequestHeader HttpHeaders headers) {
        logger.info("[getPriceInfo][Get price info]");
//        return ok(service.queryPriceInformation(headers));
        Response response = service.queryPriceInformation(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/consignprice/config")
    public HttpEntity getPriceConfig(@RequestHeader HttpHeaders headers) {
        logger.info("[getPriceConfig][Get price config]");
//        return ok(service.getPriceConfig(headers));
        Response response = service.getPriceConfig(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/consignprice")
    public HttpEntity modifyPriceConfig(@RequestBody ConsignPrice priceConfig,
                                        @RequestHeader HttpHeaders headers) {
        logger.info("[modifyPriceConfig][Create and modify price][config: {}]", priceConfig);
//        return ok(service.createAndModifyPrice(priceConfig, headers));
        Response response = service.createAndModifyPrice(priceConfig, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }
}
