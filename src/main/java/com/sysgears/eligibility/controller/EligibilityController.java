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
            @RequestParam(required = false) Optional<String> employee_id,
            @RequestParam String employee_date_of_birth,
            @RequestParam(required = false) Optional<String> employee_first_name,
            @RequestParam(required = false) Optional<String> employee_last_name) {

        return eligibilityService.isEligiable(member_status, employee_id, employee_date_of_birth
                , employee_first_name, employee_last_name) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
