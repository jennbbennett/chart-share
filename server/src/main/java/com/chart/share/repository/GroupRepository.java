package com.chart.share.repository;

import com.chart.share.domain.CSGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/14/16.
 */
public interface GroupRepository extends MongoRepository<CSGroup, Long> {
}
