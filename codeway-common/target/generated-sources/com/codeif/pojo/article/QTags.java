package com.codeif.pojo.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTags is a Querydsl query type for Tags
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTags extends EntityPathBase<Tags> {

    private static final long serialVersionUID = -314771708L;

    public static final QTags tags = new QTags("tags");

    public final com.codeif.pojo.QBasePojo _super = new com.codeif.pojo.QBasePojo(this);

    public final SetPath<Article, QArticle> articles = this.<Article, QArticle>createSet("articles", Article.class, QArticle.class, PathInits.DIRECT2);

    public final StringPath color = createString("color");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final StringPath icon = createString("icon");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath slug = createString("slug");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public QTags(String variable) {
        super(Tags.class, forVariable(variable));
    }

    public QTags(Path<? extends Tags> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTags(PathMetadata metadata) {
        super(Tags.class, metadata);
    }

}

