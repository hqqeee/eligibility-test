package com.sysgears.eligibility.service;

import com.sysgears.eligibility.mapper.EligibilityMapper;
import com.sysgears.eligibility.model.EligibilityRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EligibilityServiceTest {

    @Mock
    private EligibilityMapper eligibilityMapper;

    @InjectMocks
    private EligibilityService eligibilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEligible_Employee_Valid() {
        String employeeId = "12345";
        String dob = "1990-01-01";
        EligibilityRecord mockRecord = new EligibilityRecord();

        when(eligibilityMapper.findEmployeeByIdAndDob(employeeId, dob)).thenReturn(Optional.of(mockRecord));

        boolean result = eligibilityService.isEligiable("employee", Optional.of(employeeId), dob, Optional.empty(), Optional.empty());

        assertTrue(result);
        verify(eligibilityMapper, times(1)).findEmployeeByIdAndDob(employeeId, dob);
    }

    @Test
    void testIsEligible_Employee_Invalid() {
        String employeeId = "12345";
        String dob = "1990-01-01";

        when(eligibilityMapper.findEmployeeByIdAndDob(employeeId, dob)).thenReturn(Optional.empty());

        boolean result = eligibilityService.isEligiable("employee", Optional.of(employeeId), dob, Optional.empty(), Optional.empty());

        assertFalse(result);
        verify(eligibilityMapper, times(1)).findEmployeeByIdAndDob(employeeId, dob);
    }

    @Test
    void testIsEligible_Dependent_Valid() {
        String firstName = "John";
        String lastName = "Doe";
        String dob = "2000-05-10";
        EligibilityRecord mockRecord = new EligibilityRecord();

        when(eligibilityMapper.findDependentByNameAndDob(firstName, lastName, dob)).thenReturn(Optional.of(mockRecord));

        boolean result = eligibilityService.isEligiable("dependent", Optional.empty(), dob, Optional.of(firstName), Optional.of(lastName));

        assertTrue(result);
        verify(eligibilityMapper, times(1)).findDependentByNameAndDob(firstName, lastName, dob);
    }

    @Test
    void testIsEligible_Dependent_Invalid() {
        String firstName = "John";
        String lastName = "Doe";
        String dob = "2000-05-10";

        when(eligibilityMapper.findDependentByNameAndDob(firstName, lastName, dob)).thenReturn(Optional.empty());

        boolean result = eligibilityService.isEligiable("dependent", Optional.empty(), dob, Optional.of(firstName), Optional.of(lastName));

        assertFalse(result);
        verify(eligibilityMapper, times(1)).findDependentByNameAndDob(firstName, lastName, dob);
    }

    @Test
    void testIsEligible_InvalidMemberStatus() {
        boolean result = eligibilityService.isEligiable("unknown", Optional.empty(), "2000-05-10", Optional.empty(), Optional.empty());

        assertFalse(result);
        verifyNoInteractions(eligibilityMapper);
    }
}
