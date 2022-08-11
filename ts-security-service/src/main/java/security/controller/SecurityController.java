package security.controller;

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
import security.entity.*;
import security.service.SecurityService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/securityservice")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping(value = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "welcome to [Security Service]";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/securityConfigs")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = SecurityConfig.class,responseContainer = "ArrayList")
    })
    public HttpEntity findAllSecurityConfig(@RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[findAllSecurityConfig][Find All]");
        return ok(securityService.findAllSecurityConfig(headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/securityConfigs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "SecurityConfig",dataType = "SecurityConfig", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = SecurityConfig.class)
    })
    public HttpEntity create(@RequestBody SecurityConfig info, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[addNewSecurityConfig][Create][SecurityConfig Name: {}]", info.getName());
        return ok(securityService.addNewSecurityConfig(info, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/securityConfigs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "SecurityConfig",dataType = "SecurityConfig", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = SecurityConfig.class)
    })
    public HttpEntity update(@RequestBody SecurityConfig info, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[modifySecurityConfig][Update][SecurityConfig Name: {}]", info.getName());
        return ok(securityService.modifySecurityConfig(info, headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/securityConfigs/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity delete(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[deleteSecurityConfig][Delete][SecurityConfig Id: {}]", id);
        return ok(securityService.deleteSecurityConfig(id, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/securityConfigs/{accountId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "accountId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = String.class)
    })
    public HttpEntity check(@PathVariable String accountId, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[check][Check Security][Check Account Id: {}]", accountId);
        return ok(securityService.check(accountId, headers));
    }

}
