package com.biolense.lab.ms.service.serviceImpl;

import com.biolense.lab.ms.service.model.ParameterMaster;
import com.biolense.lab.ms.service.model.TestMaster;
import com.biolense.lab.ms.service.repository.ParameterManagmentRepo;
import com.biolense.lab.ms.service.repository.TestManagementRepo;
import com.biolense.lab.ms.service.service.TestManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestManagementServiceImpl implements TestManagementService {

    @Autowired
    private TestManagementRepo testManagementRepo;

    @Autowired
    private ParameterManagmentRepo parameterManagmentRepo;

    @Override
    public List<TestMaster> getAllTestList() {
        log.info("list :{}", testManagementRepo.findAll());
        return (List<TestMaster>) testManagementRepo.findAll();
    }
    @Override
    public TestMaster getTestDetailsById(Long testId) {
        Iterable<TestMaster> testMaster =  testManagementRepo.findAllById(Collections.singletonList(testId));
        log.info("test Detaild :{}", testMaster);
        return testMaster.iterator().next();
    }

    @Override
    public TestMaster saveTestDetails(TestMaster testMaster) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        Random random = new Random();
        TestMaster testDetails = null;
        try {
            if (!StringUtils.isEmpty(testMaster.getId())) {
                Optional<TestMaster> dbTestMaster = testManagementRepo.findById(testMaster.getId());
                if (dbTestMaster.isPresent()) {
                    TestMaster toBeUpdate = dbTestMaster.get();
                    updateTestMaster(toBeUpdate, testMaster);
                    testDetails = testManagementRepo.save(toBeUpdate);
                }
            } else {
                testMaster.setTestCode(String.valueOf(random.nextInt(9999)));
                testMaster.setCreatedBy("user");
                testMaster.setCreateAt(sdf.parse(sdf.format(currentDate)));
                testMaster.setLastUpdatedAt(sdf.parse(sdf.format(currentDate)));
                testDetails = testManagementRepo.save(testMaster);
            }
        } catch (Exception e) {
            log.error(" Error while saving test details: {}", e.getMessage());
        }
        return testDetails;
    }

    @Override
    public ParameterMaster saveParameterDetails(ParameterMaster parameterMaster) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        Optional<TestMaster> dbTestMaster = null;
        Set<ParameterMaster> updatedParameterList = new HashSet<>();
        try {
            if (!ObjectUtils.isEmpty(parameterMaster.getTestId())) {
                dbTestMaster = testManagementRepo.findById(parameterMaster.getTestId());
            }
            boolean flag = true;
            if (dbTestMaster != null && dbTestMaster.isPresent()) {
                //update scenario
                if (!CollectionUtils.isEmpty(dbTestMaster.get().getParameterMasterList())) {
                    Set<ParameterMaster> parameterMasterDb = dbTestMaster.get().getParameterMasterList();
                    for (ParameterMaster parameterMasterExisting : parameterMasterDb) {
                        ParameterMaster updatedParameter = new ParameterMaster();
                        if (!StringUtils.isEmpty(parameterMasterExisting.getParamId()) && parameterMasterExisting.getParamId().equals(parameterMaster.getParamId())) {
                            setUpdatedParameters(parameterMasterExisting, parameterMaster);
                            parameterMasterExisting.setLastUpdatedAt(sdf.parse(sdf.format(currentDate)));
                            parameterMasterExisting.setCreatedBy("user");
                            updatedParameterList.add(parameterMasterExisting);
                            flag = false;
                        }
                        else {
                            updatedParameterList.add(parameterMasterExisting);
                        }

                    }
                    //update sceanrio if new parameter added in existing parameter list.
                    if (flag) {
                        parameterMaster.setTestId(parameterMaster.getTestId());
                        parameterMaster.setLastUpdatedAt(sdf.parse(sdf.format(currentDate)));
                        parameterMaster.setCreateAt(sdf.parse(sdf.format(currentDate)));
                        parameterMaster.setCreatedBy("user");
                        parameterMaster.setLabId(1001L);
                        updatedParameterList.add(parameterMaster);
                    }
                    dbTestMaster.get().setParameterMasterList(updatedParameterList);
                    testManagementRepo.save(dbTestMaster.get());
                    parameterManagmentRepo.saveAll(updatedParameterList);
                } else { // first timw parameter added
                    parameterMaster.setTestId(parameterMaster.getTestId());
                    parameterMaster.setLastUpdatedAt(sdf.parse(sdf.format(currentDate)));
                    parameterMaster.setCreateAt(sdf.parse(sdf.format(currentDate)));
                    parameterMaster.setCreatedBy("user");
                    parameterMaster.setLabId(1001L);
                    updatedParameterList.add(parameterMaster);
                    dbTestMaster.get().setParameterMasterList(updatedParameterList);
                    testManagementRepo.save(dbTestMaster.get());
                    parameterManagmentRepo.saveAll(updatedParameterList);
                }
            }
        } catch (Exception e) {
            log.error(" Error while saving parameter details: {}", e.getMessage());
        }
        return parameterMaster;
    }

    @Override
    public Set<ParameterMaster> getParameterDetailsById(Long testId) {
        Optional<TestMaster> testMaster =  testManagementRepo.findById(testId);
        Set<ParameterMaster> parameterMasterSet=null;
        if (testMaster.isPresent()){
            if (!CollectionUtils.isEmpty(testMaster.get().getParameterMasterList())){
                parameterMasterSet = testMaster.get().getParameterMasterList().stream().sorted((p1, p2) -> p1.getPositionInPdf().compareTo(p2.getPositionInPdf())).collect(Collectors.toCollection(LinkedHashSet::new));

            }
        }
        log.info("parameter Details :{}", parameterMasterSet);
        return parameterMasterSet;
    }

    @Override
    public boolean deleteParameterDetailsById(Long paramId) {
        try {
            parameterManagmentRepo.deleteById(paramId);
            return true;
        }catch (Exception e) {
            log.error("Error while deleting parameter : {}", e.getMessage());
        }
        return false;
    }

    private void setUpdatedParameters(ParameterMaster updatedParameter, ParameterMaster parameterMaster) {
        updatedParameter.setParameterName(parameterMaster.getParameterName());
        updatedParameter.setUnit(parameterMaster.getUnit());
        updatedParameter.setMethod(parameterMaster.getMethod());
        updatedParameter.setIntegrationCode(parameterMaster.getIntegrationCode());
        updatedParameter.setPositionInPdf(parameterMaster.getPositionInPdf());
        updatedParameter.setMaleRangeFrom(parameterMaster.getMaleRangeFrom());
        updatedParameter.setMaleRangeTo(parameterMaster.getMaleRangeTo());
        updatedParameter.setFemaleRangeFrom(parameterMaster.getFemaleRangeFrom());
        updatedParameter.setFemaleRangeTo(parameterMaster.getFemaleRangeTo());
        updatedParameter.setDescription(parameterMaster.getDescription());
        updatedParameter.setDescriptiveMaleRange(parameterMaster.getDescriptiveMaleRange());
        updatedParameter.setDescriptiveFemaleRange(parameterMaster.getDescriptiveFemaleRange());
        updatedParameter.setHeadingPosition(parameterMaster.getHeadingPosition());
    }

    private void updateTestMaster(TestMaster dbTestMaster, TestMaster testMaster) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        dbTestMaster.setTestName(testMaster.getTestName());
        dbTestMaster.setCreatedBy("user");
        dbTestMaster.setTestCode(testMaster.getTestCode());
        dbTestMaster.setCostOfTest(testMaster.getCostOfTest());
        dbTestMaster.setDepartmentName(testMaster.getDepartmentName());
        dbTestMaster.setTestMethod(testMaster.getTestMethod());
        dbTestMaster.setMachineType(dbTestMaster.getMachineType());
        dbTestMaster.setLastUpdatedAt(sdf.parse(sdf.format(currentDate)));
        dbTestMaster.setTestType(testMaster.getTestType());
        dbTestMaster.setTestPrintName(testMaster.getTestPrintName());
        dbTestMaster.setMinSalePrice(testMaster.getMinSalePrice());
        dbTestMaster.setPrice(testMaster.getPrice());
        dbTestMaster.setSampleType(testMaster.getSampleType());
        dbTestMaster.setShortCutKey(testMaster.getShortCutKey());
        dbTestMaster.setLabId(testMaster.getLabId());
        dbTestMaster.setParameterSapce(testMaster.getParameterSapce());
        dbTestMaster.setTestSchedule(testMaster.getTestSchedule());
        dbTestMaster.setOutsourcingCenter(testMaster.getOutsourcingCenter());
        dbTestMaster.setCreateAt(testMaster.getCreateAt());
        dbTestMaster.setParameterMasterList(testMaster.getParameterMasterList());
        dbTestMaster.setTestType(testMaster.getTestType());
        dbTestMaster.setTestReportedOn(testMaster.getTestReportedOn());
        dbTestMaster.setMachineType(testMaster.getMachineType());
    }
}
