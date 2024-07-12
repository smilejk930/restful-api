package kr.app.restfulapi.domain.common.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1193235115L;

    public static final QUser user = new QUser("user");

    public final kr.app.restfulapi.global.entity.QBaseEntity _super = new kr.app.restfulapi.global.entity.QBaseEntity(this);

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

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

    public final StringPath userId = createString("userId");

    public final StringPath userNm = createString("userNm");

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

