package security.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity findAllSecurityConfig(@RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[findAllSecurityConfig][Find All]");
//        return ok(securityService.findAllSecurityConfig(headers));
        Response response =securityService.findAllSecurityConfig(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/securityConfigs")
    public HttpEntity create(@RequestBody SecurityConfig info, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[addNewSecurityConfig][Create][SecurityConfig Name: {}]", info.getName());
//        return ok(securityService.addNewSecurityConfig(info, headers));
        Response response =securityService.addNewSecurityConfig(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/securityConfigs")
    public HttpEntity update(@RequestBody SecurityConfig info, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[modifySecurityConfig][Update][SecurityConfig Name: {}]", info.getName());
//        return ok(securityService.modifySecurityConfig(info, headers));
        Response response =securityService.modifySecurityConfig(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/securityConfigs/{id}")
    public HttpEntity delete(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[deleteSecurityConfig][Delete][SecurityConfig Id: {}]", id);
//        return ok(securityService.deleteSecurityConfig(id, headers));
        Response response =securityService.deleteSecurityConfig(id, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/securityConfigs/{accountId}")
    public HttpEntity check(@PathVariable String accountId, @RequestHeader HttpHeaders headers) {
        SecurityController.LOGGER.info("[check][Check Security][Check Account Id: {}]", accountId);
//        return ok(securityService.check(accountId, headers));
        Response response =securityService.check(accountId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

}
