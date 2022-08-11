package config.controller;

import config.entity.Config;
import config.service.ConfigService;
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
import springfox.documentation.annotations.ApiIgnore;


import static org.springframework.http.ResponseEntity.ok;

/**
 * @author  Chenjie Xu
 * @date 2017/5/11.
 */
@RestController
@RequestMapping("api/v1/configservice")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Config Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/configs")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find all  config success",response = Config.class,responseContainer = "List")
    })
    public HttpEntity queryAll(@RequestHeader HttpHeaders headers) {
        logger.info("[queryAll][Query all configs]");
        return ok(configService.queryAll(headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "Config",dataType = "Config", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create success",response = Config.class)
    })
    public HttpEntity<?> createConfig(@RequestBody Config info, @RequestHeader HttpHeaders headers) {
        logger.info("[createConfig][Create config][Config name: {}]", info.getName());
        return new ResponseEntity<>(configService.create(info, headers), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "Config",dataType = "Config", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update success",response = Config.class)
    })
    public HttpEntity updateConfig(@RequestBody Config info, @RequestHeader HttpHeaders headers) {
        logger.info("[updateConfig][Update config][Config name: {}]", info.getName());
        return ok(configService.update(info, headers));
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/configs/{configName}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configName", value = "configName",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete success",response = Config.class)
    })
    public HttpEntity deleteConfig(@PathVariable String configName, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteConfig][Delete config][configName: {}]", configName);
        return ok(configService.delete(configName, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/configs/{configName}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configName", value = "configName",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Config.class)
    })
    public HttpEntity retrieve(@PathVariable String configName, @RequestHeader HttpHeaders headers) {
        logger.info("[retrieve][Retrieve config][configName: {}]", configName);
        return ok(configService.query(configName, headers));
    }



}
