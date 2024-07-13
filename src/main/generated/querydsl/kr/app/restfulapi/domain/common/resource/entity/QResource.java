package kr.app.restfulapi.domain.common.resource.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResource is a Querydsl query type for Resource
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResource extends EntityPathBase<Resource> {

    private static final long serialVersionUID = 493183451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResource resource = new QResource("resource");

    public final ListPath<Resource, QResource> children = this.<Resource, QResource>createList("children", Resource.class, QResource.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final StringPath httpMethod = createString("httpMethod");

    public final QResource parent;

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final SetPath<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole> roles = this.<kr.app.restfulapi.domain.common.role.entity.Role, kr.app.restfulapi.domain.common.role.entity.QRole>createSet("roles", kr.app.restfulapi.domain.common.role.entity.Role.class, kr.app.restfulapi.domain.common.role.entity.QRole.class, PathInits.DIRECT2);

    public final StringPath urlPattern = createString("urlPattern");

    public QResource(String variable) {
        this(Resource.class, forVariable(variable), INITS);
    }

    public QResource(Path<? extends Resource> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResource(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResource(PathMetadata metadata, PathInits inits) {
        this(Resource.class, metadata, inits);
    }

    public QResource(Class<? extends Resource> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QResource(forProperty("parent"), inits.get("parent")) : null;
    }

}

