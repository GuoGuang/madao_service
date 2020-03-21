package com.codeif.pojo.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -1211624855L;

    public static final QCategory category = new QCategory("category");

    public final com.codeif.pojo.QBasePojo _super = new com.codeif.pojo.QBasePojo(this);

    public final SetPath<Article, QArticle> article = this.<Article, QArticle>createSet("article", Article.class, QArticle.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath parentId = createString("parentId");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final StringPath summary = createString("summary");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath userId = createString("userId");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

