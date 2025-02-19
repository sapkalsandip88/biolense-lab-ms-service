package com.biolense.lab.ms.service.service;

import com.biolense.lab.ms.service.model.ParameterMaster;
import com.biolense.lab.ms.service.model.TestMaster;
import org.aspectj.weaver.ast.Test;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface TestManagementService {
    List<TestMaster> getAllTestList();

    TestMaster getTestDetailsById(Long testId);

    TestMaster saveTestDetails(TestMaster testMaster) throws ParseException;

    ParameterMaster saveParameterDetails(ParameterMaster parameterMaster) throws ParseException;

    Set<ParameterMaster> getParameterDetailsById(Long testId);

    boolean deleteParameterDetailsById( Long paramId);
}
