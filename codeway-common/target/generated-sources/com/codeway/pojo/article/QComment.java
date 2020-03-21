package com.codeway.pojo.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 1048231828L;

    public static final QComment comment = new QComment("comment");

    public final com.codeway.pojo.QBasePojo _super = new com.codeway.pojo.QBasePojo(this);

    public final StringPath articleId = createString("articleId");

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath id = createString("id");

    public final StringPath parentId = createString("parentId");

    public final StringPath publishDate = createString("publishDate");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath userId = createString("userId");

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

