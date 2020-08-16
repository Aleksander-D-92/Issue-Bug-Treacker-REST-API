package com.secure.secure_back_end.controllers.for_example_integration_tests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ForExampleIntegrationTests
{

    @GetMapping("/integration-test-example/hello/{userId}")
    public ResponseEntity<String> getHello(@PathVariable(value = "userId", required = false) int userId)
    {
        return new ResponseEntity<>("hello " + userId, HttpStatus.OK);
    }

    @GetMapping("/integration-test-example/json")
    public JSONObject returnJSON()
    {
        return new JSONObject("SomeTitle", "SomeValue");
    }

    @PostMapping("/integration-test-example/json")
    public ResponseEntity<String> postJSON(@Valid @RequestBody JSONObject jsonObject, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>("adsasd", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("dasddas", HttpStatus.OK);
    }

    @GetMapping("/integration-test-example/users-only")
    public ResponseEntity<String> privateResource()
    {
        return new ResponseEntity<>("Private resource", HttpStatus.OK);
    }
}
