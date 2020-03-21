package com.codeway.pojo.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginLog is a Querydsl query type for LoginLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoginLog extends EntityPathBase<LoginLog> {

    private static final long serialVersionUID = -2060590267L;

    public static final QLoginLog loginLog = new QLoginLog("loginLog");

    public final com.codeway.pojo.QBasePojo _super = new com.codeway.pojo.QBasePojo(this);

    public final StringPath browser = createString("browser");

    public final StringPath clientIp = createString("clientIp");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath id = createString("id");

    public final StringPath osInfo = createString("osInfo");

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath userId = createString("userId");

    public QLoginLog(String variable) {
        super(LoginLog.class, forVariable(variable));
    }

    public QLoginLog(Path<? extends LoginLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginLog(PathMetadata metadata) {
        super(LoginLog.class, metadata);
    }

}

