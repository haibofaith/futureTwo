package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
public class FriendsPicParentList implements Serializable{
    private List<FriendPicParent> friendPicParents;

    public List<FriendPicParent> getFriendPicParents() {
        return friendPicParents;
    }

    public void setFriendPicParents(List<FriendPicParent> friendPicParents) {
        this.friendPicParents = friendPicParents;
    }
}
