package com.biolense.lab.ms.service.model;

import javafx.beans.binding.DoubleExpression;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "parameter_master")
public class ParameterMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long paramId;
    @Column(name = "TEST_ID")
    private Long testId;
    @Column(name = "LAB_ID")
    private Long labId;
    @Column(name = "PARAMETER_NAME")
    private String parameterName;
    @Column(name = "METHOD")
    private String method;
    @Column(name = "POSITION_IN_PDF")
    private Integer positionInPdf;
    @Column(name = "MALE_RANGE_FROM")
    private Double maleRangeFrom;
    @Column(name = "MALE_RANGE_TO")
    private Double maleRangeTo;
    @Column(name = "FEMALE_RANGE_FROM")
    private Double femaleRangeFrom;
    @Column(name = "FEMALE_RANGE_TO")
    private Double femaleRangeTo;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "INTEGRATION_CODE")
    private String integrationCode;
    @Column(name = "DESCRIPTIVE_MALE_RANGE")
    private String descriptiveMaleRange;
    @Column(name = "DESCRIPTIVE_FEMALE_RANGE")
    private String descriptiveFemaleRange;
    @Column(name = "DESCRIPTION")
    private String description;
  /*  @Column(name = "ENTER_DICTIONARY")
    private List<String> enterDictionary;*/
    @Column(name = "HEADING_POSITION")
    private String headingPosition;
    @Column(name = "PARAM_TYPE")
    private String paramType;
    @Column(name = "SETTINGS")
    private String settings;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "create_At")
    private Date createAt;
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;
}
