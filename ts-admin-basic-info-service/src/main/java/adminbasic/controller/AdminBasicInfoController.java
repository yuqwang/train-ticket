package adminbasic.controller;

import adminbasic.entity.*;
import adminbasic.service.AdminBasicInfoService;
import edu.fudan.common.entity.*;
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
@RequestMapping("/api/v1/adminbasicservice")
public class AdminBasicInfoController {

    @Autowired
    AdminBasicInfoService adminBasicInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminBasicInfoController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ AdminBasicInfo Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/contacts")
    @ApiResponses({
            @ApiResponse(code=1, message = "success", response= Contacts.class,responseContainer = "ArrayList")
    })
    public HttpEntity getAllContacts(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllContacts][Find All Contacts by admin][getAllContacts] ");
        return ok(adminBasicInfoService.getAllContacts(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/contacts/{contactsId}")
    @ApiImplicitParam(name = "contactsId", dataType = "String", paramType = "path", required = true,defaultValue = "75ce3fe9-5cdb-49e9-bd27-6df080a2f38b")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Delete success", response = UUID.class),
            @ApiResponse(code = 0, message = "Delete failed", response = UUID.class)
    })
    public HttpEntity deleteContacts(@PathVariable String contactsId, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteContacts][Delete Contacts by admin][contactsId: {}]", contactsId);
        return ok(adminBasicInfoService.deleteContact(contactsId, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/contacts")
    @ApiImplicitParam(name = "mci",value = "Contacts",dataType = "Contacts", paramType = "body",required = true)
    @ApiResponses({
            @ApiResponse(code = 0, message = "Contacts not found"),
            @ApiResponse(code = 1, message = "Modify success", response = Contacts.class)
    })
    public HttpEntity modifyContacts(@RequestBody Contacts mci, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyContacts][Modify Contacts by admin][Contacts name:{}]", mci.getName());
        return ok(adminBasicInfoService.modifyContact(mci, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/contacts")
    @ApiImplicitParam(name = "c", value = "Contacts",dataType = "Contacts", paramType = "body",required = true)
    @ApiResponses({
            @ApiResponse(code = 0, message = "Already Exists", response = Contacts.class),
            @ApiResponse(code = 1, message = "Create Success")
    })
    public HttpEntity addContacts(@RequestBody Contacts c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addContacts][Modify Contacts by admin][Contacts name: {}]", c.getName());
        return ok(adminBasicInfoService.addContact(c, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/stations")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find all content", response = Station.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No content")
    })
    public HttpEntity getAllStations(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllStations][Find All Station by admin][getAllStations]");
        return ok(adminBasicInfoService.getAllStations(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/stations/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Delete success", response = Station.class),
            @ApiResponse(code = 0, message = "Station not exist")
    })
    public HttpEntity deleteStation(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteStation][Delete Station by admin][Station id: {}]", id);
        return ok(adminBasicInfoService.deleteStation(id, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/stations")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Station not exist"),
            @ApiResponse(code = 1, message = "Update success", response = Station.class)
    })
    public HttpEntity modifyStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyStation][Modify Station by admin][Station id: {}]", s.getId());
        return ok(adminBasicInfoService.modifyStation(s, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/stations")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Create success", response = Station.class),
            @ApiResponse(code = 0, message = "Already exists", response = Station.class)
    })
    public HttpEntity addStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addStation][Add Station by admin][Station id: {}]", s.getId());
        return ok(adminBasicInfoService.addStation(s, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/trains")
    @ApiResponses({
            @ApiResponse(code = 1, message = "success", response = TrainType.class,responseContainer = "List") ,
            @ApiResponse(code = 0, message = "no content", response = TrainType.class,responseContainer = "List")
    })
    public HttpEntity getAllTrains(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllTrains][Find All Train by admin][getAllStations]");
        return ok(adminBasicInfoService.getAllTrains(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/trains/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true,defaultValue = "GaoTieOne")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "delete success", response =boolean.class),
            @ApiResponse(code = 0, message = "there is no train according to id")
    })
    public HttpEntity deleteTrain(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteTrain][Delete Train by admin][train id: {}]", id);
        return ok(adminBasicInfoService.deleteTrain(id, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/trains")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "TrainType",dataType = "TrainType", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "update success",response = boolean.class),
            @ApiResponse(code = 0, message = "there is no trainType with the trainType id",response = boolean.class)
    })
    public HttpEntity modifyTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyTrain][Modify Train by admin][TrainType id: {}]", t.getId());
        return ok(adminBasicInfoService.modifyTrain(t, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/trains")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "TrainType",dataType = "TrainType", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "create success"),
            @ApiResponse(code = 0, message = "train type already exist", response = TrainType.class)
    })
    public HttpEntity addTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addTrain][Add Train by admin][TrainType id: {}]", t.getId());
        return ok(adminBasicInfoService.addTrain(t, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/configs")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find all  config success",response = Config.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No content")
    })
    public HttpEntity getAllConfigs(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllConfigs][Find All Config by admin][getAllConfigs]");
        return ok(adminBasicInfoService.getAllConfigs(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/configs/{name}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name",dataType = "String", paramType = "path",required = true,defaultValue = "DirectTicketAllocationProportion")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config doesn't exist."),
            @ApiResponse(code = 1, message = "Delete success", response = Config.class)
    })
    public HttpEntity deleteConfig(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteConfig][Delete Config by admin][Config name: {}]", name);
        return ok(adminBasicInfoService.deleteConfig(name, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "Config",dataType = "Config", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config doesn't exist."),
            @ApiResponse(code = 1, message = "Update success", response = Config.class)
    })
    public HttpEntity modifyConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyConfig][Modify Config by admin][Config name: {}]", c.getName());
        return ok(adminBasicInfoService.modifyConfig(c, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "Config",dataType = "Config", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config already exists."),
            @ApiResponse(code = 1, message = "Create success", response = Config.class)
    })
    public HttpEntity addConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addConfig][Add Config by admin][Config name: {}]", c.getName());
        return ok(adminBasicInfoService.addConfig(c, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/prices")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Success",response = PriceConfig.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No price config")
    })
    public HttpEntity getAllPrices(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllPrices][Find All Price by admin][getAllPrices]");
        return ok(adminBasicInfoService.getAllPrices(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/prices/{pricesId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "No that config"),
            @ApiResponse(code = 1, message = "Delete success",response = PriceConfig.class)
    })
    public HttpEntity deletePrice(@PathVariable String pricesId, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deletePrice][Delete Price by admin][PriceInfo id: {}]", pricesId);
        return ok(adminBasicInfoService.deletePrice(pricesId, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "No that config"),
            @ApiResponse(code = 1, message = "Update success",response = PriceConfig.class)
    })
    public HttpEntity modifyPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyPrice][Modify Price by admin][PriceInfo id: {}]", pi.getId());
        return ok(adminBasicInfoService.modifyPrice(pi, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Create success",response = PriceConfig.class)
    })
    public HttpEntity addPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addPrice][Add Price by admin[PriceInfo id: {}]", pi.getId());
        return ok(adminBasicInfoService.addPrice(pi, headers));
    }

}
