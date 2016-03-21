package com.chart.share.repository;

import com.chart.share.domain.Activity;
import com.chart.share.domain.DomainType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by jenn on 3/21/16.
 */
public interface ActivityRepository extends MongoRepository<Activity, ObjectId>{
    List<Activity> findByTargetTypeAndTargetId(DomainType targetType, Long targetId);
    List<Activity> findByGroupIdOrderByActivityDateDesc(Long groupId);
}
