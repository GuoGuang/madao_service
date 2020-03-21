package com.codeif.pojo.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptLog is a Querydsl query type for OptLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptLog extends EntityPathBase<OptLog> {

    private static final long serialVersionUID = 885025339L;

    public static final QOptLog optLog = new QOptLog("optLog");

    public final com.codeif.pojo.QBasePojo _super = new com.codeif.pojo.QBasePojo(this);

    public final StringPath browser = createString("browser");

    public final StringPath clientIp = createString("clientIp");

    //inherited
    public final NumberPath<Long> createAt = _super.createAt;

    public final StringPath exceptionDetail = createString("exceptionDetail");

    public final StringPath id = createString("id");

    public final StringPath method = createString("method");

    public final StringPath osInfo = createString("osInfo");

    public final StringPath params = createString("params");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> updateAt = _super.updateAt;

    public final StringPath userId = createString("userId");

    public QOptLog(String variable) {
        super(OptLog.class, forVariable(variable));
    }

    public QOptLog(Path<? extends OptLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptLog(PathMetadata metadata) {
        super(OptLog.class, metadata);
    }

}

