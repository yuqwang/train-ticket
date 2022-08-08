package auth.controller;


import auth.dto.BasicAuthDto;
import auth.dto.TokenDto;
import auth.entity.User;
import auth.exception.UserOperationException;
import auth.service.TokenService;
import auth.service.UserService;
import edu.fudan.common.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/hello")
    public Object getHello() {
        return "Hello";
    }

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dao", value = "BasicAuthDto",dataType = "BasicAuthDto", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Verification failed."),
            @ApiResponse(code = 0, message = "Incorrect username or password."),
            @ApiResponse(code = 1, message = "login success",response = TokenDto.class)
    })
    public ResponseEntity<Response> getToken(@RequestBody BasicAuthDto dao , @RequestHeader HttpHeaders headers) {
        logger.info("Login request of username: {}", dao.getUsername());
        try {
            Response<?> res = tokenService.getToken(dao, headers);
            return ResponseEntity.ok(res);
        } catch (UserOperationException e) {
            logger.error("[getToken][tokenService.getToken error][UserOperationException, message: {}]", e.getMessage());
            return ResponseEntity.ok(new Response<>(0, "get token error", null));
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllUser][Get all users]");
        return ResponseEntity.ok().body(userService.getAllUser(headers));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteUserById(@PathVariable String userId, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteUserById][Delete user][userId: {}]", userId);
        return ResponseEntity.ok(userService.deleteByUserId(userId, headers));
    }

}
