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

    public Optional<EligibilityRecord> checkEmployeeEligibility(String employeeId, String dateOfBirth) {
        return eligibilityMapper.findEmployeeByIdAndDob(employeeId, dateOfBirth);
    }

    public Optional<EligibilityRecord> checkDependentEligibility(String firstName, String lastName, String dateOfBirth) {
        return eligibilityMapper.findDependentByNameAndDob(firstName, lastName, dateOfBirth);
    }
}
