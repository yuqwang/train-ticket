package price.controller;

import edu.fudan.common.entity.TravelResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import price.entity.PriceConfig;
import price.service.PriceService;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/priceservice")
public class PriceController {

    @Autowired
    PriceService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

    @GetMapping(path = "/prices/welcome")
    public String home() {
        return "Welcome to [ Price Service ] !";
    }

    @GetMapping(value = "/prices/{routeId}/{trainType}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeId", value = "routeId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "trainType", value = "trainType",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class)
    })
    public HttpEntity query(@PathVariable String routeId, @PathVariable String trainType,
                            @RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[findByRouteIdAndTrainType][Query price][RouteId: {}, TrainType: {}]",routeId,trainType);
        return ok(service.findByRouteIdAndTrainType(routeId, trainType, headers));
    }

    @PostMapping(value = "/prices/byRouteIdsAndTrainTypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ridsAndTts", value = "ridsAndTts",dataType="String", allowMultiple = true,paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class,responseContainer = "Map<String, PriceConfig>")
    })
    public HttpEntity query(@RequestBody List<String> ridsAndTts,
                            @RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[findByRouteIdAndTrainType][Query price][routeId and Train Type: {}]", ridsAndTts);
        return ok(service.findByRouteIdsAndTrainTypes(ridsAndTts, headers));
    }

    @GetMapping(value = "/prices")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class,responseContainer = "List")
    })
    public HttpEntity queryAll(@RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[findAllPriceConfig][Query all prices]");
        return ok(service.findAllPriceConfig(headers));
    }

    @PostMapping(value = "/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "PriceConfig",dataType = "PriceConfig", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class)
    })
    public HttpEntity<?> create(@RequestBody PriceConfig info,
                                @RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[createNewPriceConfig][Create price][RouteId: {}, TrainType: {}]",info.getRouteId(),info.getTrainType());
        return new ResponseEntity<>(service.createNewPriceConfig(info, headers), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/prices/{pricesId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pricesId", value = "pricesId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class)
    })
    public HttpEntity delete(@PathVariable String pricesId, @RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[deletePriceConfig][Delete price][PriceConfigId: {}]",pricesId);
        return ok(service.deletePriceConfig(pricesId, headers));
    }

    @PutMapping(value = "/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "PriceConfig",dataType = "PriceConfig", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PriceConfig.class)
    })
    public HttpEntity update(@RequestBody PriceConfig info, @RequestHeader HttpHeaders headers) {
        PriceController.LOGGER.info("[updatePriceConfig][Update price][PriceConfigId: {}]",info.getId());
        return ok(service.updatePriceConfig(info, headers));
    }
}
