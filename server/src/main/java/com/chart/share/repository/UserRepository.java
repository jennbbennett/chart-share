package com.chart.share.repository;

import com.chart.share.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/13/16.
 */
public interface UserRepository extends MongoRepository<User, Long> {
    public User findByAuthId(String authId);
}
