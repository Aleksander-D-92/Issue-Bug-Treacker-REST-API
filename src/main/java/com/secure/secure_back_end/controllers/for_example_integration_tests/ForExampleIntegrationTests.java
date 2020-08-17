package com.secure.secure_back_end.controllers.for_example_integration_tests;

import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import com.secure.secure_back_end.dto.user.UserLoginForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ForExampleIntegrationTests
{

//    @GetMapping("/integration-test-example/hello/{userId}")
//    public ResponseEntity<String> getHello(@PathVariable(value = "userId", required = false) int userId)
//    {
//        return new ResponseEntity<>("hello " + userId, HttpStatus.OK);
//    }
//
//    @GetMapping("/integration-test-example/json")
//    public JSONObject returnJSON()
//    {
//        return new JSONObject("SomeTitle", "SomeValue");
//    }
//
//    @PostMapping("/integration-test-example/json")
//    public ResponseEntity<String> postJSON(@Valid @RequestBody JSONObject jsonObject, BindingResult bindingResult)
//    {
//        if (bindingResult.hasErrors())
//        {
//            return new ResponseEntity<>("adsasd", HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>("dasddas", HttpStatus.OK);
//    }
//
//    @GetMapping("/integration-test-example/users-only")
//    public ResponseEntity<String> privateResource()
//    {
//        return new ResponseEntity<>("Private resource", HttpStatus.OK);
//    }


    @GetMapping("/integration-test-example/get-user/{id}")
    @ApiOperation(value = "integer", notes = "test UserNotFoundException.class")
    public String testException1(@PathVariable(value = "id") int id)
    {
        if (id < 0)
        {
            throw new UserNotFoundException("nekav message");
        } else
        {
            return "user";
        }
    }

    @PostMapping("/integration-test-example/post-user/")
    @ApiOperation(value = "string", notes = "test @Valid @RequestBody UserLoginForm userLoginForm 400")
    public String testException1(@Valid @RequestBody UserLoginForm userLoginForm)
    {
        System.out.println(userLoginForm);
        return "you logged in";
    }
}
