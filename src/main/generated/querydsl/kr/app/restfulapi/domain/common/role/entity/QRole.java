package kr.app.restfulapi.domain.common.role.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -1121636821L;

    public static final QRole role = new QRole("role");

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final SetPath<kr.app.restfulapi.domain.common.resource.entity.Resource, kr.app.restfulapi.domain.common.resource.entity.QResource> resources = this.<kr.app.restfulapi.domain.common.resource.entity.Resource, kr.app.restfulapi.domain.common.resource.entity.QResource>createSet("resources", kr.app.restfulapi.domain.common.resource.entity.Resource.class, kr.app.restfulapi.domain.common.resource.entity.QResource.class, PathInits.DIRECT2);

    public final StringPath roleId = createString("roleId");

    public final SetPath<kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser, kr.app.restfulapi.domain.common.user.gnrl.entity.QGnrlUser> users = this.<kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser, kr.app.restfulapi.domain.common.user.gnrl.entity.QGnrlUser>createSet("users", kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser.class, kr.app.restfulapi.domain.common.user.gnrl.entity.QGnrlUser.class, PathInits.DIRECT2);

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

