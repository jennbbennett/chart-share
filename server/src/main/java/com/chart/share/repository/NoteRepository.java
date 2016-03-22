package com.chart.share.repository;

import com.chart.share.domain.DomainType;
import com.chart.share.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by jenn on 3/20/16.
 */
public interface NoteRepository extends MongoRepository<Note, Long> {
    List<Note> findByTargetTypeAndTargetId(DomainType targetType, Long targetId);
    List<Note> findByGroupIdOrderByDateAddedDesc(Long groupId);
}
