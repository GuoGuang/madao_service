package com.youyd.article.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * 问答板块:回答表
 */

public class Reply  extends BasePojo implements Serializable {
    private String id;
    private String problemId;//问题ID
    private String content;//回答内容
    private String userId;//回答人ID
    private String nickName;//回答人昵称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}