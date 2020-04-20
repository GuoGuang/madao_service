package com.codeway.pojo.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1701184571L;

    public static final QUser user = new QUser("user");

    public final com.codeway.pojo.QBasePojo _super = new com.codeway.pojo.QBasePojo(this);

    public final StringPath account = createString("account");

    public final StringPath avatar = createString("avatar");

    public final NumberPath<Long> birthday = createNumber("birthday", Long.class);

    public final StringPath contactAddress = createString("contactAddress");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath email = createString("email");

    public final NumberPath<Integer> fansCount = createNumber("fansCount", Integer.class);

    public final NumberPath<Integer> followCount = createNumber("followCount", Integer.class);

    public final StringPath id = createString("id");

    public final StringPath interest = createString("interest");

    public final NumberPath<Long> lastDate = createNumber("lastDate", Long.class);

    public final StringPath nickName = createString("nickName");

    public final NumberPath<Long> onlineTime = createNumber("onlineTime", Long.class);

    public final StringPath password = createString("password");

    public final StringPath personality = createString("personality");

    public final StringPath phone = createString("phone");

    public final StringPath registeredType = createString("registeredType");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath userName = createString("userName");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

