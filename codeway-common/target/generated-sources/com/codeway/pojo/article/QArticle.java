package com.codeway.pojo.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -634544597L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final com.codeway.pojo.QBasePojo _super = new com.codeway.pojo.QBasePojo(this);

    public final QCategory category;

    public final NumberPath<Integer> comment = createNumber("comment", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath image = createString("image");

    public final NumberPath<Float> importance = createNumber("importance", Float.class);

    public final NumberPath<Integer> isPublic = createNumber("isPublic", Integer.class);

    public final NumberPath<Integer> isTop = createNumber("isTop", Integer.class);

    public final StringPath keywords = createString("keywords");

    public final NumberPath<Integer> origin = createNumber("origin", Integer.class);

    public final NumberPath<Integer> reviewState = createNumber("reviewState", Integer.class);

    public final SetPath<Tags, QTags> tags = this.<Tags, QTags>createSet("tags", Tags.class, QTags.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final NumberPath<Integer> upvote = createNumber("upvote", Integer.class);

    public final StringPath url = createString("url");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> visits = createNumber("visits", Integer.class);

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

