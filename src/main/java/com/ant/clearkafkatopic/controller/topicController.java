package com.ant.clearkafkatopic.controller;

import com.ant.clearkafkatopic.commonsAndUtils.Message;
import com.ant.clearkafkatopic.commonsAndUtils.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wolf   2018/11/30
 */
@RestController
public class topicController {

    public  Integer count =1;

    @Autowired public Producer producer;

    @GetMapping("/send")
    public ResponseEntity send(@RequestParam(name = "message",defaultValue = "say hello") String message){
        producer.send(new Message(count,message));
        count+=1;
        return new ResponseEntity(message, HttpStatus.OK);
    }



}
