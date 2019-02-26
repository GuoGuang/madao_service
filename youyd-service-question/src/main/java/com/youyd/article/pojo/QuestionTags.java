package com.youyd.article.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description:  问题_标签_中间表
 * @author: LGG
 * @create: 2019-02-26 16:21
 **/

public class QuestionTags extends BasePojo implements Serializable {

    private Integer id; // 问题_标签_中间表id
    private Integer questionId; // 问题表id
    private Integer tagsId; // 标签表id

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionId=").append(questionId);
        sb.append(", tagsId=").append(tagsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}