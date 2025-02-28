package com.sysgears.eligibility.controller;

import com.sysgears.eligibility.model.EligibilityRecord;
import com.sysgears.eligibility.service.EligibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkEligibility(
            @RequestParam String employe_code,
            @RequestParam String member_status,
            @RequestParam(required = false) String employee_id,
            @RequestParam String employee_date_of_birth,
            @RequestParam(required = false) String employee_first_name,
            @RequestParam(required = false) String employee_last_name) {

        Optional<EligibilityRecord> eligibilityRecord;
        System.out.println(employee_id);
        if ("employee".equalsIgnoreCase(member_status)) {
            if (employee_id == null) {
                return ResponseEntity.badRequest().body("employee_id is required for employees.");
            }
            eligibilityRecord = eligibilityService.checkEmployeeEligibility(employee_id, employee_date_of_birth);
        } else if ("dependent".equalsIgnoreCase(member_status)) {
            if (employee_first_name == null || employee_last_name == null) {
                return ResponseEntity.badRequest().body("First and last name are required for dependents.");
            }
            eligibilityRecord = eligibilityService.checkDependentEligibility(employee_first_name, employee_last_name, employee_date_of_birth);
        } else {
            return ResponseEntity.badRequest().body("Invalid member_status.");
        }
        eligibilityRecord.stream().toList().forEach(System.out::println);
        return eligibilityRecord.map(a -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
