package com.secure.secure_back_end.controllers.heroku_dummu;

import com.secure.secure_back_end.dto.rest_success.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WakeUpHeroku
{
    @PostMapping("/heroku/start")
    @ApiOperation(value = "Send a request to heroku right after the front end loads, because if you are using the free hosting the app \"hibernates\" and the first request takes 20sec")
    public ResponseEntity<Message> wakeUpTheDyno()
    {
        return new ResponseEntity<>(new Message("app woke up"), HttpStatus.OK);
    }
}
