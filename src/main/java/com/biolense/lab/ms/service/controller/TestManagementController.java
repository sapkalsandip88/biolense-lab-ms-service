package com.biolense.lab.ms.service.controller;

import com.biolense.lab.ms.service.model.TestRequestDto;
import com.biolense.lab.ms.service.model.Tests;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/test-management")
public class TestManagementController {

    @PostMapping("/saveTest")
    public ResponseEntity<String> saveTest(@RequestBody TestRequestDto testRequestDto){

        return null;
    }

    @GetMapping("/testList")
    public ResponseEntity<Tests> getTestAllTestList(){

        return null;
    }
}
