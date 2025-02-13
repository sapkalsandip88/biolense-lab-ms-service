package com.biolense.lab.ms.service.serviceImpl;

import com.biolense.lab.ms.service.model.Tests;
import com.biolense.lab.ms.service.repository.TestManagementRepo;
import com.biolense.lab.ms.service.service.TestManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TestManagementServiceImpl implements TestManagementService {

    @Autowired
    private TestManagementRepo testManagementRepo;

    @Override
    public List<Tests> getAllTestList() {
        return (List<Tests>) testManagementRepo.findAll();
    }
}
