package kr.app.restfulapi.domain.common.user.gnrl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGnrlUser is a Querydsl query type for GnrlUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGnrlUser extends EntityPathBase<GnrlUser> {

    private static final long serialVersionUID = -249367381L;

    public static final QGnrlUser gnrlUser = new QGnrlUser("gnrlUser");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    public final StringPath lgnId = createString("lgnId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    public final StringPath pswd = createString("pswd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final SetPath<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole> roles = this.<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole>createSet("roles", kr.app.restfulapi.domain.common.role.entity.Role.class, kr.app.restfulapi.domain.common.role.entity.QRole.class, PathInits.DIRECT2);

    public final StringPath userNm = createString("userNm");

    public final StringPath userTsid = createString("userTsid");

    public QGnrlUser(String variable) {
        super(GnrlUser.class, forVariable(variable));
    }

    public QGnrlUser(Path<? extends GnrlUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGnrlUser(PathMetadata metadata) {
        super(GnrlUser.class, metadata);
    }

}

