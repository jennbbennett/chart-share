package com.chart.share.repository;

import com.chart.share.domain.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by jenn on 3/16/16.
 */
public interface MedicationRepository extends MongoRepository<Medication, Long> {
    List<Medication> findByGroupId(Long groupId);
}
