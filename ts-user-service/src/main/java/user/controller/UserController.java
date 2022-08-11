package user.controller;

import edu.fudan.common.entity.TravelResult;
import edu.fudan.common.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.dto.UserDto;
import user.entity.User;
import user.service.UserService;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/userservice/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/hello")
    public String testHello() {
        return "Hello";
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = User.class,responseContainer = "List")
    })
    public ResponseEntity<Response> getAllUser(@RequestHeader HttpHeaders headers) {
        UserController.LOGGER.info("[getAllUser][Get all user]");
        return ok(userService.getAllUsers(headers));
    }

    @GetMapping("/{userName}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "userName",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = User.class)
    })
    public ResponseEntity<Response> getUserByUserName(@PathVariable String userName, @RequestHeader HttpHeaders headers) {
        UserController.LOGGER.info("[getUserByUserName][Get user by user name][UserName: {}]",userName);
        return ok(userService.findByUserName(userName, headers));
    }

    @GetMapping("/id/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = User.class)
    })
    public ResponseEntity<Response> getUserByUserId(@PathVariable String userId, @RequestHeader HttpHeaders headers) {
        UserController.LOGGER.info("[getUserByUserId][Get user by user id][UserId: {}]",userId);
        return ok(userService.findByUserId(userId, headers));
    }

    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDto", value = "UserDto",dataType = "UserDto", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = User.class)
    })
    public ResponseEntity<Response> registerUser(@RequestBody UserDto userDto, @RequestHeader HttpHeaders headers) {
        UserController.LOGGER.info("[registerUser][Register user][UserName: {}]",userDto.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto, headers));
    }


    @DeleteMapping("/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public ResponseEntity<Response> deleteUserById(@PathVariable String userId,
                                                   @RequestHeader HttpHeaders headers) {
        // only admin token can delete
        UserController.LOGGER.info("[deleteUserById][Delete user][UserId: {}]",userId);
        return ok(userService.deleteUser(userId, headers));
    }

    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user",dataType = "UserDto", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = User.class)
    })
    public ResponseEntity<Response> updateUser(@RequestBody UserDto user,
                                               @RequestHeader HttpHeaders headers) {
        UserController.LOGGER.info("[updateUser][Update user][UserId: {}]",user.getUserId());
        return ok(userService.updateUser(user, headers));
    }

}
