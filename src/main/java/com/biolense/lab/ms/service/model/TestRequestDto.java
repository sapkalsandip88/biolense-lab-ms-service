package com.biolense.lab.ms.service.model;

import lombok.Data;

import java.util.Date;
@Data
public class TestRequestDto {
    private String testName;
    private String testCode;
    private String testPrintName;
    private String shortCutKey;
    private Double price;
    private Double minSalePrice;
    private Double costOfTest;
    private String departmentName;
    private String testType;
    private int parameterSapce;
    private String testMethod;
    private String testSchedule;
    private String testReportedOn;
    private String sampleType;
    private String machineType;
}
