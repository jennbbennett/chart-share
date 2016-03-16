package com.chart.share.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by jenn on 3/16/16.
 */
public class Sequence {
    @Id
    ObjectId id;
    long seq;

    public Sequence(long seq) {
        this.seq = seq;
    }
}
