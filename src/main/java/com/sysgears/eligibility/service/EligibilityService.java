package com.sysgears.eligibility.service;

import com.sysgears.eligibility.mapper.EligibilityMapper;
import com.sysgears.eligibility.model.EligibilityRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EligibilityService {

    private final EligibilityMapper eligibilityMapper;

    public EligibilityService(EligibilityMapper eligibilityMapper) {
        this.eligibilityMapper = eligibilityMapper;
    }


    public boolean isEligiable(String memberStatus, Optional<String> employeeIdOpt,
                               String employeeDateOfBirth, Optional<String> employeeFirstNameOpt,
                               Optional<String> employeeLastNameOpt) {
        return switch (memberStatus) {
            case "employee" -> checkEmployeeEligibility(employeeIdOpt, employeeDateOfBirth).isPresent();
            case "dependent" ->
                    checkDependentEligibility(employeeFirstNameOpt, employeeLastNameOpt, employeeDateOfBirth).isPresent();
            default -> false;
        };
    }


    private Optional<EligibilityRecord> checkEmployeeEligibility(Optional<String> employeeIdOpt, String dateOfBirth) {
        return employeeIdOpt.flatMap(employeeId -> eligibilityMapper.findEmployeeByIdAndDob(employeeId, dateOfBirth));
    }

    private Optional<EligibilityRecord> checkDependentEligibility(Optional<String> firstNameOpt, Optional<String> lastNameOpt,
                                                                  String dateOfBirth) {
        return firstNameOpt.flatMap(firstName ->
                lastNameOpt.flatMap(lastName ->
                        eligibilityMapper.findDependentByNameAndDob(firstName, lastName, dateOfBirth)));
    }
}
