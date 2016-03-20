package com.chart.share.repository;

import com.chart.share.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jenn on 3/20/16.
 */
public interface NoteRepository extends MongoRepository<Note, Long> {
}
