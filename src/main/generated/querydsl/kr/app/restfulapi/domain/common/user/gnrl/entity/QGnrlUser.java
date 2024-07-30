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
    public final StringPath lastServerNm = _super.lastServerNm;

    public final StringPath lgnId = createString("lgnId");

    public final StringPath pswd = createString("pswd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registDt = _super.registDt;

    //inherited
    public final StringPath registerId = _super.registerId;

    //inherited
    public final StringPath registServerNm = _super.registServerNm;

    public final SetPath<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole> roles = this.<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole>createSet("roles", kr.app.restfulapi.domain.common.role.entity.Role.class, kr.app.restfulapi.domain.common.role.entity.QRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDt = _super.updtDt;

    //inherited
    public final StringPath updusrId = _super.updusrId;

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

