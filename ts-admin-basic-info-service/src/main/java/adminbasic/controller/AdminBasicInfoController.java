package adminbasic.controller;

import adminbasic.entity.*;
import adminbasic.service.AdminBasicInfoService;
import edu.fudan.common.entity.Config;
import edu.fudan.common.entity.Contacts;
import edu.fudan.common.entity.Station;
import edu.fudan.common.entity.TrainType;
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
    public HttpEntity getAllContacts(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllContacts][Find All Contacts by admin][getAllContacts] ");
        Response response = adminBasicInfoService.getAllContacts(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//        return ok(adminBasicInfoService.getAllContacts(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/contacts/{contactsId}")
    public HttpEntity deleteContacts(@PathVariable String contactsId, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteContacts][Delete Contacts by admin][contactsId: {}]", contactsId);
//        return ok(adminBasicInfoService.deleteContact(contactsId, headers));
        Response response = adminBasicInfoService.deleteContact(contactsId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/contacts")
    public HttpEntity modifyContacts(@RequestBody Contacts mci, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyContacts][Modify Contacts by admin][Contacts name:{}]", mci.getName());
//        return ok(adminBasicInfoService.modifyContact(mci, headers));
        Response response = adminBasicInfoService.modifyContact(mci, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/contacts")
    public HttpEntity addContacts(@RequestBody Contacts c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addContacts][Modify Contacts by admin][Contacts name: {}]", c.getName());
//        return ok(adminBasicInfoService.addContact(c, headers));
        Response response = adminBasicInfoService.addContact(c, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/stations")
    public HttpEntity getAllStations(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllStations][Find All Station by admin][getAllStations]");
//        return ok(adminBasicInfoService.getAllStations(headers));
        Response response = adminBasicInfoService.getAllStations(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/stations/{id}")
    public HttpEntity deleteStation(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteStation][Delete Station by admin][Station id: {}]", id);
//        return ok(adminBasicInfoService.deleteStation(id, headers));
        Response response = adminBasicInfoService.deleteStation(id, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/stations")
    public HttpEntity modifyStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyStation][Modify Station by admin][Station id: {}]", s.getId());
//        return ok(adminBasicInfoService.modifyStation(s, headers));
        Response response = adminBasicInfoService.modifyStation(s, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/stations")
    public HttpEntity addStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addStation][Add Station by admin][Station id: {}]", s.getId());
//        return ok(adminBasicInfoService.addStation(s, headers));
        Response response = adminBasicInfoService.addStation(s, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/trains")
    public HttpEntity getAllTrains(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllTrains][Find All Train by admin][getAllStations]");
//        return ok(adminBasicInfoService.getAllTrains(headers));
        Response response = adminBasicInfoService.getAllTrains(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/trains/{id}")
    public HttpEntity deleteTrain(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteTrain][Delete Train by admin][train id: {}]", id);
//        return ok(adminBasicInfoService.deleteTrain(id, headers));
        Response response = adminBasicInfoService.deleteTrain(id, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/trains")
    public HttpEntity modifyTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyTrain][Modify Train by admin][TrainType id: {}]", t.getId());
//        return ok(adminBasicInfoService.modifyTrain(t, headers));
        Response response = adminBasicInfoService.modifyTrain(t, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/trains")
    public HttpEntity addTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addTrain][Add Train by admin][TrainType id: {}]", t.getId());
//        return ok(adminBasicInfoService.addTrain(t, headers));
        Response response = adminBasicInfoService.addTrain(t, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/configs")
    public HttpEntity getAllConfigs(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllConfigs][Find All Config by admin][getAllConfigs]");
//        return ok(adminBasicInfoService.getAllConfigs(headers));
        Response response = adminBasicInfoService.getAllConfigs(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/configs/{name}")
    public HttpEntity deleteConfig(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deleteConfig][Delete Config by admin][Config name: {}]", name);
//        return ok(adminBasicInfoService.deleteConfig(name, headers));
        Response response = adminBasicInfoService.deleteConfig(name, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/configs")
    public HttpEntity modifyConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyConfig][Modify Config by admin][Config name: {}]", c.getName());
//        return ok(adminBasicInfoService.modifyConfig(c, headers));
        Response response = adminBasicInfoService.modifyConfig(c, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/configs")
    public HttpEntity addConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addConfig][Add Config by admin][Config name: {}]", c.getName());
//        return ok(adminBasicInfoService.addConfig(c, headers));
        Response response = adminBasicInfoService.addConfig(c, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/prices")
    public HttpEntity getAllPrices(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[getAllPrices][Find All Price by admin][getAllPrices]");
//        return ok(adminBasicInfoService.getAllPrices(headers));
        Response response = adminBasicInfoService.getAllPrices(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/prices/{pricesId}")
    public HttpEntity deletePrice(@PathVariable String pricesId, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[deletePrice][Delete Price by admin][PriceInfo id: {}]", pricesId);
//        return ok(adminBasicInfoService.deletePrice(pricesId, headers));
        Response response = adminBasicInfoService.deletePrice(pricesId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/prices")
    public HttpEntity modifyPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[modifyPrice][Modify Price by admin][PriceInfo id: {}]", pi.getId());
//        return ok(adminBasicInfoService.modifyPrice(pi, headers));
        Response response = adminBasicInfoService.modifyPrice(pi, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/prices")
    public HttpEntity addPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[addPrice][Add Price by admin[PriceInfo id: {}]", pi.getId());
//        return ok(adminBasicInfoService.addPrice(pi, headers));
        Response response = adminBasicInfoService.addPrice(pi, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

}
