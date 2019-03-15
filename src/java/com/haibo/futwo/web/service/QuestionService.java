package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.Question;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/11
 * @description:
 */
public interface QuestionService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int insertQuestions(List<Question> questions);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<Question> selectQuestionsByQNum(String qNum);
}
