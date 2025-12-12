package com.mtp.ehr.service;

import org.springframework.data.cassandra.core.ExecutableSelectOperation;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mtp.ehr.entity.PatientEntity;
import com.mtp.ehr.model.Patient;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;

@Service
public class EHRPatientService {
    private final Gson GSON = new Gson();

    private final CassandraTemplate cassandraTemplate1;

    public EHRPatientService(@Autowired CassandraTemplate cassandraTemplate1) {
        this.cassandraTemplate1 = cassandraTemplate1;
    }

    public PatientEntity registerPatient(String patientDataJson) {

        Patient patient = GSON.fromJson(patientDataJson, Patient.class);

        String mrnId = patient.getIdentifier().get(0).getValue();
        System.out.println("Processing patient with MRN ID: " + mrnId);
        String document = GSON.toJson(patient);
        Instant now = Instant.now();
        PatientEntity patientEntity = new PatientEntity(mrnId, document, now, now);

        return cassandraTemplate1.insert(patientEntity);
    }

    public List<PatientEntity> getPatient() {
        return cassandraTemplate1.select("select *from patients", PatientEntity.class);
    }

    public PatientEntity getPatientByMrnId(String mrnId) {
        return cassandraTemplate1.selectOne("select *from patients where mrn_id=" + "'" + mrnId + "'", PatientEntity.class);
    }

}
