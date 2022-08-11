package travelplan.controller;

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
import org.springframework.web.bind.annotation.*;
import edu.fudan.common.entity.TripInfo;
import travelplan.entity.TransferTravelInfo;
import travelplan.entity.TransferTravelResult;
import travelplan.entity.TravelAdvanceResultUnit;
import travelplan.service.TravelPlanService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("api/v1/travelplanservice")
public class TravelPlanController {

    @Autowired
    TravelPlanService travelPlanService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelPlanController.class);

    @GetMapping(path = "/welcome" )
    public String home() {
        return "Welcome to [ TravelPlan Service ] !";
    }

    @PostMapping(value="/travelPlan/transferResult" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "TransferTravelInfo",dataType = "TransferTravelInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = TransferTravelResult.class)
    })
    public HttpEntity getTransferResult(@RequestBody TransferTravelInfo info, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getTransferSearch][Search Transit][start: {},end: {}]",info.getStartStation(),info.getEndStation());
        return ok(travelPlanService.getTransferSearch(info, headers));
    }

    @PostMapping(value="/travelPlan/cheapest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryInfo", value = "TripInfo",dataType = "TripInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = TravelAdvanceResultUnit.class,responseContainer = "ArrayList")
    })
    public HttpEntity getByCheapest(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getCheapest][Search Cheapest][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getCheapest(queryInfo, headers));
    }

    @PostMapping(value="/travelPlan/quickest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryInfo", value = "TripInfo",dataType = "TripInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = TravelAdvanceResultUnit.class,responseContainer = "ArrayList")
    })
    public HttpEntity getByQuickest(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getQuickest][Search Quickest][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getQuickest(queryInfo, headers));
    }

    @PostMapping(value="/travelPlan/minStation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryInfo", value = "TripInfo",dataType = "TripInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = TravelAdvanceResultUnit.class,responseContainer = "ArrayList")
    })
    public HttpEntity getByMinStation(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getMinStation][Search Min Station][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getMinStation(queryInfo, headers));
    }

}
