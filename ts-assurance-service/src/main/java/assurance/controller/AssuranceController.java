package assurance.controller;

import assurance.entity.Assurance;
import assurance.entity.AssuranceTypeBean;
import assurance.entity.PlainAssurance;
import assurance.service.AssuranceService;
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

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/assuranceservice")
public class AssuranceController {

    @Autowired
    private AssuranceService assuranceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AssuranceController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Assurance Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/assurances")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[getAllAssurances][Get All Assurances]",response = PlainAssurance.class,responseContainer = "ArrayList"),
            @ApiResponse(code = 0, message = "Assurance is Empty")
    })
    public HttpEntity getAllAssurances(@RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[getAllAssurances][Get All Assurances]");
        return ok(assuranceService.getAllAssurances(headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/assurances/types")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[getAllAssuranceType][Get Assurance Type]",response = AssuranceTypeBean.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "Assurance is Empty")
    })
    public HttpEntity getAllAssuranceType(@RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[getAllAssuranceType][Get Assurance Type]");
        return ok(assuranceService.getAllAssuranceTypes(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/assurances/assuranceid/{assuranceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assuranceId", value = "assuranceId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete Success with Assurance id"),
            @ApiResponse(code = 0, message = "Fail.Assurance not clear",response = String.class)
    })
    public HttpEntity deleteAssurance(@PathVariable String assuranceId, @RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[deleteAssurance][Delete Assurance][assuranceId: {}]", assuranceId);
        return ok(assuranceService.deleteById(UUID.fromString(assuranceId), headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/assurances/orderid/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete Success with Order Id"),
            @ApiResponse(code = 0, message = "Fail.Assurance not clear",response = String.class)
    })
    public HttpEntity deleteAssuranceByOrderId(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[deleteAssuranceByOrderId][Delete Assurance by orderId][orderId: {}]", orderId);
        return ok(assuranceService.deleteByOrderId(UUID.fromString(orderId), headers));
    }

    @CrossOrigin(origins = "*")
    @PatchMapping(path = "/assurances/{assuranceId}/{orderId}/{typeIndex}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assuranceId", value = "assuranceId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "typeIndex", value = "typeIndex",dataType = "int", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Modify Success",response = Assurance.class),
            @ApiResponse(code = 0, message = "Fail.Assurance not clear")
    })
    public HttpEntity modifyAssurance(@PathVariable String assuranceId,
                                      @PathVariable String orderId,
                                      @PathVariable int typeIndex, @RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[modifyAssurance][Modify Assurance][assuranceId: {}, orderId: {}, typeIndex: {}]",
                assuranceId, orderId, typeIndex);
        return ok(assuranceService.modify(assuranceId, orderId, typeIndex, headers));
    }


    @CrossOrigin(origins = "*")
    @GetMapping(path = "/assurances/{typeIndex}/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "typeIndex", value = "typeIndex",dataType = "int", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Assurance.class),
            @ApiResponse(code = 0, message = "Fail.Assurance type doesn't exist")
    })
    public HttpEntity createNewAssurance(@PathVariable int typeIndex, @PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        //Assurance
        AssuranceController.LOGGER.info("[createNewAssurance][Create new assurance][typeIndex: {}, orderId: {}]", typeIndex, orderId);
        return ok(assuranceService.create(typeIndex, orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/assurances/assuranceid/{assuranceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assuranceId", value = "assuranceId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find Assurance Success",response = Assurance.class),
            @ApiResponse(code = 0, message = "No Content by this id")
    })
    public HttpEntity getAssuranceById(@PathVariable String assuranceId, @RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[getAssuranceById][Find assurance by assuranceId][assureId: {}]", assuranceId);
        return ok(assuranceService.findAssuranceById(UUID.fromString(assuranceId), headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/assurance/orderid/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find Assurance Success"),
            @ApiResponse(code = 0, message = "No Content by this orderId",response = String.class)
    })
    public HttpEntity findAssuranceByOrderId(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        AssuranceController.LOGGER.info("[findAssuranceByOrderId][Find assurance by orderId][orderId: {}]", orderId);
        return ok(assuranceService.findAssuranceByOrderId(UUID.fromString(orderId), headers));
    }

}
