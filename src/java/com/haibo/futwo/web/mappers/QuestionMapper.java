package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    int insertQuestions(@Param("list") List<Question> questions);
    List<Question> selectQuestionsByQNum(@Param("qNum") String qNum);
}
