package com.chart.share.repository;

import com.chart.share.domain.Patient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by jenn on 3/20/16.
 */
public interface PatientRepository extends MongoRepository<Patient, ObjectId> {
    List<Patient> findByPhysicianId(long physicianId);
    void deleteByPersonId(long personId);

}
