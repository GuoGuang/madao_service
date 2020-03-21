package com.codeif.pojo.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDict is a Querydsl query type for Dict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDict extends EntityPathBase<Dict> {

    private static final long serialVersionUID = -455279392L;

    public static final QDict dict = new QDict("dict");

    public final com.codeif.pojo.QBasePojo _super = new com.codeif.pojo.QBasePojo(this);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath parentId = createString("parentId");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public QDict(String variable) {
        super(Dict.class, forVariable(variable));
    }

    public QDict(Path<? extends Dict> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDict(PathMetadata metadata) {
        super(Dict.class, metadata);
    }

}

