package com.chart.share.repository;

import com.chart.share.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/15/16.
 */
public interface AddressRepository extends MongoRepository<Address, Long> {
}
