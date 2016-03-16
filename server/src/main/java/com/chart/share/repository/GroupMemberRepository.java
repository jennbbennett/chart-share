package com.chart.share.repository;

import com.chart.share.domain.GroupMember;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by jenn on 3/14/16.
 */
public interface GroupMemberRepository extends MongoRepository<GroupMember,Long> {
    GroupMember findByMemberId(Long memberId);
    List<GroupMember> findByGroupId(Long groupId);
}
