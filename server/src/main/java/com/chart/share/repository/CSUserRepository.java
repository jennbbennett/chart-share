package com.chart.share.repository;

import com.chart.share.domain.CSUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/13/16.
 */
public interface CSUserRepository extends MongoRepository<CSUser, Long> {
    public CSUser findByAuthId(String authId);
}
