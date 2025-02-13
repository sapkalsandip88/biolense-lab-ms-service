package com.biolense.lab.ms.service.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Entity
@Table(name = "test_master")
public class Tests {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "test_name")
    private String testName;
    @Column(name = "test_code")
    private String testCode;
    @Column(name = "test_print_name")
    private String testPrintName;
    @Column(name = "short_cut_cey")
    private String shortCutKey;
    @Column(name = "price")
    private Double price;
    @Column(name = "min_sale_price")
    private Double minSalePrice;
    @Column(name = "cost_of_test")
    private Double costOfTest;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "test_type")
    private String testType;
    @Column(name = "parameter_sapce")
    private int parameterSapce;
    @Column(name = "test_method")
    private String testMethod;
    @Column(name = "test_schedule")
    private String testSchedule;
    @Column(name = "test_reported_on")
    private String testReportedOn;
    @Column(name = "sample_type")
    private String sampleType;
    @Column(name = "machine_type")
    private String machineType;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "create_At")
    private Date createAt;
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;



}
