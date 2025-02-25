package com.biolense.lab.ms.service.controller;

import com.biolense.lab.ms.service.model.ParameterMaster;
import com.biolense.lab.ms.service.model.TestRequestDto;
import com.biolense.lab.ms.service.model.TestMaster;
import com.biolense.lab.ms.service.repository.ParameterManagmentRepo;
import com.biolense.lab.ms.service.service.TestManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController
public class TestManagementController {

    @Autowired
    private TestManagementService testManagementService;

    @PostMapping("/saveTest")
    public ResponseEntity<String> saveTest(@RequestBody TestRequestDto testRequestDto){
        return null;
    }

    @GetMapping("/test-list")
    public ResponseEntity<List<TestMaster>> getTestAllTestList(){
        List<TestMaster> testMasterList = testManagementService.getAllTestList();
        return new ResponseEntity<>(testMasterList, HttpStatus.OK);
    }
    @GetMapping("/test-details/")
    public ResponseEntity<TestMaster> getTestDetailsById(@RequestParam("testId") Long testId){
        TestMaster testMaster = testManagementService.getTestDetailsById(testId);
        return new ResponseEntity<>(testMaster, HttpStatus.OK);
    }

    @PostMapping("/save-details")
    public ResponseEntity<TestMaster> saveTestDetails(@RequestBody TestMaster testMaster) throws ParseException {
        TestMaster testMasterReturn= testManagementService.saveTestDetails(testMaster);
        return new ResponseEntity<>(testMasterReturn, HttpStatus.OK);
    }
    @PostMapping("/save-parameters")
    public ResponseEntity<ParameterMaster> saveParameterDetails(@RequestBody ParameterMaster parameterMaster) throws ParseException {
        ParameterMaster parameterMasterReturn= testManagementService.saveParameterDetails(parameterMaster);
        return new ResponseEntity<>(parameterMasterReturn, HttpStatus.OK);
    }
    @GetMapping("/parameter-details/")
    public ResponseEntity<Set<ParameterMaster>> getParameterDetailsById(@RequestParam("testId") Long testId){
        Set<ParameterMaster> parameterMasterSet = testManagementService.getParameterDetailsById(testId);
        return new ResponseEntity<>(parameterMasterSet, HttpStatus.OK);
    }
    @GetMapping("/delete-parameter/")
    public ResponseEntity<Boolean> deleteParameterDetailsById(@RequestParam("paramId") Long paramId){
        boolean deleted = testManagementService.deleteParameterDetailsById( paramId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
    @GetMapping("/delete-test-details/")
    public ResponseEntity<Boolean> deleteTestDetailsById(@RequestParam("testdId") Long testId){
        boolean deleted = testManagementService.deleteTestDetailsById(testId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
