package adminuser.controller;

import adminuser.dto.UserDto;
import adminuser.service.AdminUserService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/adminuserservice/users")
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ AdminUser Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public HttpEntity getAllUsers(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllUsers][Get all users]");
//        return ok(adminUserService.getAllUsers(headers));
        Response response = adminUserService.getAllUsers(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @PutMapping
    public HttpEntity updateUser(@RequestBody UserDto userDto, @RequestHeader HttpHeaders headers) {
        logger.info("[updateUser][Update User][userName: {}]", userDto.getUserName());
//        return ok(adminUserService.updateUser(userDto, headers));
        Response response = adminUserService.updateUser(userDto, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }


    @PostMapping
    public HttpEntity addUser(@RequestBody UserDto userDto, @RequestHeader HttpHeaders headers) {
        logger.info("[addUser][Add user][userName: {}]", userDto.getUserName());
//        return ok(adminUserService.addUser(userDto, headers));
        Response response = adminUserService.addUser(userDto, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @DeleteMapping(value = "/{userId}")
    public HttpEntity deleteUser(@PathVariable String userId, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteUser][Delete user][userId: {}]", userId);
//        return ok(adminUserService.deleteUser(userId, headers));
        Response response = adminUserService.deleteUser(userId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

}
