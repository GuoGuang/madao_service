package com.codeway.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasePojo is a Querydsl query type for BasePojo
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBasePojo extends EntityPathBase<BasePojo> {

    private static final long serialVersionUID = -226082712L;

    public static final QBasePojo basePojo = new QBasePojo("basePojo");

    public final NumberPath<Long> createAt = createNumber("createAt", Long.class);

    public final NumberPath<Long> updateAt = createNumber("updateAt", Long.class);

    public QBasePojo(String variable) {
        super(BasePojo.class, forVariable(variable));
    }

    public QBasePojo(Path<? extends BasePojo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasePojo(PathMetadata metadata) {
        super(BasePojo.class, metadata);
    }

}

