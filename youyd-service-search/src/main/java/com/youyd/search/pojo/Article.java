package com.youyd.search.pojo;

import com.youyd.pojo.BasePojo;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @description 文章板块: 文章类,集成ES
 * @author LGG
 * @create 2018-10-14 18:44
 **/
@Document(indexName="coco",type="article")
public class Article extends BasePojo implements Serializable {

    private String id;//ID
    private String columnId;//专栏ID
    private String userId;//用户ID
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;//标题
    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;//文章正文
    private String image;//文章封面
    private String isPublic;//是否公开
    private String isTop;//是否置顶
    private Integer visits;//浏览量
    private Integer thumbUp;//点赞数
    private Integer comment;//评论数
    private String state;//审核状态
    private String channelId;//所属频道
    private String url;//URL
    private String type;//类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(Integer thumbUp) {
        this.thumbUp = thumbUp;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}