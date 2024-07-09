package kr.app.restfulapi.domain.common.role.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResourceRole is a Querydsl query type for ResourceRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResourceRole extends EntityPathBase<ResourceRole> {

    private static final long serialVersionUID = 1516972633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResourceRole resourceRole = new QResourceRole("resourceRole");

    public final kr.app.restfulapi.domain.common.resource.entity.QResource resource;

    public final NumberPath<Long> resourceRolesId = createNumber("resourceRolesId", Long.class);

    public final QRole role;

    public QResourceRole(String variable) {
        this(ResourceRole.class, forVariable(variable), INITS);
    }

    public QResourceRole(Path<? extends ResourceRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResourceRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResourceRole(PathMetadata metadata, PathInits inits) {
        this(ResourceRole.class, metadata, inits);
    }

    public QResourceRole(Class<? extends ResourceRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resource = inits.isInitialized("resource") ? new kr.app.restfulapi.domain.common.resource.entity.QResource(forProperty("resource")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

