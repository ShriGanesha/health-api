package com.mtp.ehr.controller;

import com.mtp.ehr.entity.PatientEntity;
import com.mtp.ehr.model.Patient;
import org.springframework.web.bind.annotation.*;
import com.mtp.ehr.service.EHRPatientService;

import java.util.List;

@RestController
@RequestMapping("/ehr")
public class EHRController {

    private final EHRPatientService ehrPatientService;

    public EHRController(EHRPatientService ehrPatientService) {
        this.ehrPatientService = ehrPatientService;
    }

    @GetMapping("/v1/health")
    public String getMethodName() {
        return new String("EHR Service is up and running.");
    }

    @PostMapping("/v1/patient")
    public PatientEntity registerPatient(@RequestBody String requestString) {
        return ehrPatientService.registerPatient(requestString);
    }

    @GetMapping("/v1/patient")
    public List<PatientEntity> getPatients() {
        return ehrPatientService.getPatient();
    }

    @GetMapping("/v1/patient/{mrnId}")
    public PatientEntity getPatients(@PathVariable String mrnId) {
        return ehrPatientService.getPatientByMrnId(mrnId);
    }

}
