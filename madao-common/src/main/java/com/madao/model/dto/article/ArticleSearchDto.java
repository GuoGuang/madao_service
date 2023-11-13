package com.madao.model.dto.article;

import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 文章板块: 文章类,集成ES
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@Document(indexName = "article_search")
@Setting(
		refreshInterval = "1s",// 索引的刷新间隔，默认情况下每个分片会每秒自动刷新一次。这就是为什么我们说 Elasticsearch 是 近 实时搜索: 文档的变化并不是立即对搜索可见，但会在一秒之内变为可见。
		replicas = 0, // 索引的副本数
		shards = 1,// 索引的分片数。
		indexStoreType = "fs",// 索引文件存储类型
		sortFields = {"thumbUp"},
		sortModes = {Setting.SortMode.max},
		sortOrders = {Setting.SortOrder.desc},
		sortMissingValues = {Setting.SortMissing._last}
)
public class ArticleSearchDto extends BasePojo implements Serializable {

	// 索引主键
	@Id
	private String id;
	private String columnId;
	private String userId;
	//    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	@Field(type = FieldType.Keyword)
	private String title;
	@Field(type = FieldType.Keyword)
	private String content;

	@Schema(type = "String",description = "图片")
	@Field(type = FieldType.Text)
	private String image;
	private String isPublic;
	@Schema(type = "String",description = "置顶")
	@Field(type = FieldType.Keyword)
	private String isTop;
	private Integer visits;
	@Field(type = FieldType.Integer)
	private Integer thumbUp;
	private Integer comment;
	@Field(type = FieldType.Keyword)
	private String state;
	private String channelId;
	private String url;
	@Field(type = FieldType.Text)
	private String type;
	@Field(type = FieldType.Date,format = DateFormat.date)
	private LocalDate topDate;

	private String keyword;
	private LocalDate startDate;
	private LocalDate endDate;
}