package com.chart.share.repository;

import com.chart.share.domain.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/13/16.
 */
public interface PersonRepository extends MongoRepository<Person, Long> {
}
