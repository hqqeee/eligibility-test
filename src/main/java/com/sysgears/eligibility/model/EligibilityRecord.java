package com.sysgears.eligibility.model;

import lombok.Data;

@Data
public class EligibilityRecord {
    private String memberUniqueId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String relations;
    private String subGroup;
    private String jobType;
    private String hireDate;
    private String eligibilityStartDate;
    private String eligibilityEndDate;
    private String employeeStatus;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String stateCode;
    private String zipCode;
    private String country;
    private String employeeGroup;
}
