package com.chart.share.repository;

import com.chart.share.domain.Sequence;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/16/16.
 */
public interface SequenceRepository extends MongoRepository<Sequence, ObjectId> {
}
