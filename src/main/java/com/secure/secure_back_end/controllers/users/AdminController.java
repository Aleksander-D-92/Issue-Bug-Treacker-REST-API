package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.dto.rest_success.Message;
import com.secure.secure_back_end.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class AdminController
{
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    @PutMapping("/admins/user-authority")
    @ApiOperation(value = "Changes authority by Id, @RequestParams are \"userId\" and \"authorityId\". Example PUT /admins/user-authority?userId=1&authorityId=2")
    public ResponseEntity<Message> changeAuthority(@RequestParam("userId") @Min(1) Long userId,
                                                   @RequestParam("authorityId") @Min(1) Long authorityId)
    {
        this.userService.changeUserRole(authorityId, userId);
        return new ResponseEntity<>(new Message("Successfully changed this users authority"), HttpStatus.OK);
    }

    @PutMapping("/admins/user-account-lock")
    @ApiOperation(value = " Locks user account by Id. @RequestParams are \"action\" and \"id\". Action can be \"lock\" or \"unlock\". Example GET /admins/user-account-lock?action=lock&userId=1")
    public ResponseEntity<Message> lockAccount(@RequestParam("action") @Pattern(regexp = "^lock$|^unlock$") String action,
                                               @RequestParam("userId") @Min(1) Long id)
    {
        this.userService.lockAccountAdmin(action, id);
        return new ResponseEntity<>(new Message("Successfully locked this users account"), HttpStatus.OK);
    }
}
