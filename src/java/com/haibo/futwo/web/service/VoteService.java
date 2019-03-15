package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.VoteInfo;

public interface VoteService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int newVote(VoteInfo voteInfo);
}
