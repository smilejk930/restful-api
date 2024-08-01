package kr.app.restfulapi.domain.common.user.gnrl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAuthrt is a Querydsl query type for UserAuthrt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAuthrt extends EntityPathBase<UserAuthrt> {

    private static final long serialVersionUID = 1250617780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAuthrt userAuthrt = new QUserAuthrt("userAuthrt");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    public final QGnrlUser gnrlUser;

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final StringPath userTsid = createString("userTsid");

    public final EnumPath<kr.app.restfulapi.domain.common.user.gnrl.util.UserType> userTypeCd = createEnum("userTypeCd", kr.app.restfulapi.domain.common.user.gnrl.util.UserType.class);

    public QUserAuthrt(String variable) {
        this(UserAuthrt.class, forVariable(variable), INITS);
    }

    public QUserAuthrt(Path<? extends UserAuthrt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAuthrt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAuthrt(PathMetadata metadata, PathInits inits) {
        this(UserAuthrt.class, metadata, inits);
    }

    public QUserAuthrt(Class<? extends UserAuthrt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gnrlUser = inits.isInitialized("gnrlUser") ? new QGnrlUser(forProperty("gnrlUser")) : null;
    }

}

