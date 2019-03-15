package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.VoteMapper;
import com.haibo.futwo.web.model.VoteInfo;
import com.haibo.futwo.web.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteMapper voteMapper;

    @Override
    public int newVote(VoteInfo voteInfo) {
        return voteMapper.newVote(voteInfo);
    }
}
