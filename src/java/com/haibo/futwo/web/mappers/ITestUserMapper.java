package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.TestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2018/12/20
 * @description:
 */
@Repository
public interface ITestUserMapper {
    List<TestUser> selectAllUsers();
}
