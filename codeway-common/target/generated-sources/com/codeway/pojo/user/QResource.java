package com.codeway.pojo.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResource is a Querydsl query type for Resource
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResource extends EntityPathBase<Resource> {

    private static final long serialVersionUID = 2042176638L;

    public static final QResource resource = new QResource("resource");

    public final com.codeway.pojo.QBasePojo _super = new com.codeway.pojo.QBasePojo(this);

    public final StringPath code = createString("code");

    public final StringPath component = createString("component");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final StringPath icon = createString("icon");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> isHidden = createNumber("isHidden", Integer.class);

    public final StringPath method = createString("method");

    public final StringPath name = createString("name");

    public final StringPath parentId = createString("parentId");

    public final StringPath path = createString("path");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final NumberPath<Float> sort = createNumber("sort", Float.class);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath url = createString("url");

    public QResource(String variable) {
        super(Resource.class, forVariable(variable));
    }

    public QResource(Path<? extends Resource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResource(PathMetadata metadata) {
        super(Resource.class, metadata);
    }

}

