package com.mtp.ehr.entity;

import java.time.Instant;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("patients")
public class PatientEntity {

    @PrimaryKey
    private String mrn_id;

    @Column("document")
    private String document;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    public PatientEntity() {
    }

    public PatientEntity(String mrn_id, String document, Instant createdAt, Instant updatedAt) {
        this.mrn_id = mrn_id;
        this.document = document;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getMrn_id() {
        return mrn_id;
    }

    public void setMrn_id(String mrn_id) {
        this.mrn_id = mrn_id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "mrn_id='" + mrn_id + '\'' +
                ", document='" + document + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
    

}
