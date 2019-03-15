package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.FileMapper;
import com.haibo.futwo.web.model.WeixinImg;
import com.haibo.futwo.web.model.WeixinRelation;
import com.haibo.futwo.web.service.FileService;
import com.haibo.futwo.web.service.RelationService;
import com.haibo.futwo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/3
 * @description:
 */
@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserService userService;
    @Override
    public int uploadImg(WeixinImg weixinImg) {
        return fileMapper.uploadImg(weixinImg);
    }

    @Override
    public List<String> selectImgParentDistinct(String openid) {
        return fileMapper.selectImgParentDistinct(openid);
    }

    @Override
    public List<WeixinImg> selectImgByImgFile(String imgFile,String uuid) {
        String open_id = userService.getOpenIdByUuid(uuid);
        return fileMapper.selectImgByImgFile(imgFile,open_id);
    }


    @Autowired
    private RelationService relationService;

    @Override
    public List<WeixinImg> selectFriendImgByImgFile(String imgFile, String uuid,String friendOpenid) {
        //查自己open_id
//        String open_id = userService.getOpenIdByUuid(uuid);
        //获得自己的朋友关系
//        List<WeixinRelation> relations = relationService.getRelationInfoByOpenid(open_id);
//        List<String> friendOpenid = relationService.getFriendByOpenid(open_id);
        return fileMapper.selectImgByImgFile(imgFile,friendOpenid);
    }
}
