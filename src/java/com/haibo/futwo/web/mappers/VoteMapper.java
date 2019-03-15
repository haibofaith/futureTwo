package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.VoteInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteMapper {
    int newVote(VoteInfo voteInfo);
}
