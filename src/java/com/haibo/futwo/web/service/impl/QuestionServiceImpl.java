package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.QuestionMapper;
import com.haibo.futwo.web.model.Question;
import com.haibo.futwo.web.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/11
 * @description:
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public int insertQuestions(List<Question> questions) {
        return questionMapper.insertQuestions(questions);
    }

    @Override
    public List<Question> selectQuestionsByQNum(String qNum) {

        return questionMapper.selectQuestionsByQNum(qNum);
    }
}
