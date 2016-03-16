package com.chart.share.repository;

import com.chart.share.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Created by jenn on 3/14/16.
 */
public interface GroupRepository extends MongoRepository<Group, Long> {
}
