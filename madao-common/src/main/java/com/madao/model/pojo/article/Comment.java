package com.madao.model.pojo.article;

import com.madao.model.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ar_comment",
		indexes = {
				@Index(name = "comments_article_id", columnList = "articleId"),
				@Index(name = "comments_parent_id", columnList = "parentId")
		})
public class Comment extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", columnDefinition = "char")
	private String id;

	/**
	 * 评论人ID，未接入用户系统，暂时使用用户qq号作为唯一id
	 */
	@Column(length = 20)
	private String userId;

	/**
	 * 父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)
	 */
	@Column(length = 20)
	private String parentId;

	@Column(length = 20)
	private String articleId;

	@Column(length = 200)
	private String content;

	@Column(length = 200)
	private Integer upvote;

	@Column(length = 200)
	private String avatar;

	@Column(length = 300)
	private String userName;

    @Column(length = 200)
    private String toId;

    @Column(length = 200)
    private String toName;

	@Column(length = 300)
	private String toAvatar;

}