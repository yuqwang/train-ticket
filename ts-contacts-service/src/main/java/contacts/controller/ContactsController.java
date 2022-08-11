package contacts.controller;

import contacts.entity.*;
import edu.fudan.common.util.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import contacts.service.ContactsService;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("api/v1/contactservice")
public class ContactsController {


    @Autowired
    private ContactsService contactsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);

    @GetMapping(path = "/contacts/welcome")
    public String home() {
        return "Welcome to [ Contacts Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/contacts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create contacts success",response = Contacts.class,responseContainer = "ArrayList")
    })
    public HttpEntity getAllContacts(@RequestHeader HttpHeaders headers) {
        ContactsController.LOGGER.info("[getAllContacts][Get All Contacts]");
        return ok(contactsService.getAllContacts(headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/contacts")
    @ApiOperation("createNewContacts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aci", value = "Contacts",dataType = "Contacts", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Contacts already exists"),
            @ApiResponse(code = 200, message = "Create contacts success",response = Contacts.class)
    })
    public ResponseEntity<Response> createNewContacts(@RequestBody Contacts aci,
                                                      @RequestHeader HttpHeaders headers) {
        ContactsController.LOGGER.info("[createNewContacts][VerifyLogin Success]");
        return new ResponseEntity<>(contactsService.create(aci, headers), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/contacts/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aci", value = "Contacts",dataType = "Contacts", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Contacts already exists"),
            @ApiResponse(code = 200, message = "Create contacts success",response = Contacts.class)
    })
    public HttpEntity<?> createNewContactsAdmin(@RequestBody Contacts aci, @RequestHeader HttpHeaders headers) {
        aci.setId(UUID.randomUUID().toString());
        ContactsController.LOGGER.info("[createNewContactsAdmin][Create Contacts In Admin]");
        return new ResponseEntity<>(contactsService.createContacts(aci, headers), HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/contacts/{contactsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contactsId", value = "contactsId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete success",response = String.class)
    })
    public HttpEntity deleteContacts(@PathVariable String contactsId, @RequestHeader HttpHeaders headers) {
        return ok(contactsService.delete(contactsId, headers));
    }


    @CrossOrigin(origins = "*")
    @PutMapping(path = "/contacts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "Contacts",dataType = "Contacts", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Contacts already exists"),
            @ApiResponse(code = 200, message = "Modify success",response = Contacts.class)
    })
    public HttpEntity modifyContacts(@RequestBody Contacts info, @RequestHeader HttpHeaders headers) {
        ContactsController.LOGGER.info("[Contacts modifyContacts][Modify Contacts] ContactsId: {}", info.getId());
        return ok(contactsService.modify(info, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/contacts/account/{accountId}")
    @ApiOperation("findContactsByAccountId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "accountId",dataType = "String", paramType = "path",required = true,defaultValue = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Contacts.class,responseContainer = "ArrayList")
    })
    public HttpEntity findContactsByAccountId(@PathVariable String accountId, @RequestHeader HttpHeaders headers) {
        ContactsController.LOGGER.info("[findContactsByAccountId][Find Contacts By Account Id][accountId: {}]", accountId);
        ContactsController.LOGGER.info("[ContactsService][VerifyLogin Success]");
        return ok(contactsService.findContactsByAccountId(accountId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/contacts/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true,defaultValue = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = Contacts.class)
    })
    public HttpEntity getContactsByContactsId(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        ContactsController.LOGGER.info("[ContactsService][Contacts Id Print][id: {}]", id);
        ContactsController.LOGGER.info("[ContactsService][VerifyLogin Success]");
        return ok(contactsService.findContactsById(id, headers));
    }



}
