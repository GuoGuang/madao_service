//package com.madao.model.dto.article;
//
//import com.madao.model.BasePojo;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.Setting;
//
//import java.io.Serializable;
//
///**
// * 文章板块: 文章类,集成ES
// *
// * @author GuoGuang
// * @公众号 码道人生
// * @gitHub https://github.com/GuoGuang
// * @website https://madaoo.com
// * @created 2019-09-29 7:37
// */
//@Getter
//@Setter
//@Document(indexName = "qqarticle")
//@Setting(
//		refreshInterval = "1s",// 索引的刷新间隔，默认情况下每个分片会每秒自动刷新一次。这就是为什么我们说 Elasticsearch 是 近 实时搜索: 文档的变化并不是立即对搜索可见，但会在一秒之内变为可见。
//		replicas = 0, // 索引的副本数
//		shards = 1,// 索引的分片数。
//		indexStoreType = "fs",// 索引文件存储类型
//		sortFields = {"thumbUp"},
//		sortModes = {Setting.SortMode.max},
//		sortOrders = {Setting.SortOrder.desc},
//		sortMissingValues = {Setting.SortMissing._last}
//)
//public class ArticleSearchDto extends BasePojo implements Serializable {
//
//	private String id;
//	private String columnId;
//	private String userId;
//	//    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
//	@Field(type = FieldType.Text)
//	private String title;
//	@Field(type = FieldType.Text)
//	private String content;
//	private String image;
//	private String isPublic;
//	private String isTop;
//	private Integer visits;
//	@Field(type = FieldType.Integer)
//	private Integer thumbUp;
//	private Integer comment;
//	private String state;
//	private String channelId;
//	private String url;
//	private String type;
//}