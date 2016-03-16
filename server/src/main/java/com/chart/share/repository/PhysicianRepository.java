package com.chart.share.repository;

import com.chart.share.domain.Physician;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/15/16.
 */
public interface PhysicianRepository extends MongoRepository<Physician, Long>{
}
