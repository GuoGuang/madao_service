package com.codeif.pojo.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1701091558L;

    public static final QRole role = new QRole("role");

    public final com.codeif.pojo.QBasePojo _super = new com.codeif.pojo.QBasePojo(this);

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath id = createString("id");

    public final SetPath<Resource, QResource> resources = this.<Resource, QResource>createSet("resources", Resource.class, QResource.class, PathInits.DIRECT2);

    public final StringPath roleCode = createString("roleCode");

    public final StringPath roleDesc = createString("roleDesc");

    public final StringPath roleName = createString("roleName");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

