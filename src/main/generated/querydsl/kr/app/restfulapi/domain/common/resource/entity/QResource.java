package kr.app.restfulapi.domain.common.resource.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResource is a Querydsl query type for Resource
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResource extends EntityPathBase<Resource> {

    private static final long serialVersionUID = 493183451L;

    public static final QResource resource = new QResource("resource");

    public final StringPath description = createString("description");

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final StringPath urlPattern = createString("urlPattern");

    public QResource(String variable) {
        super(Resource.class, forVariable(variable));
    }

    public QResource(Path<? extends Resource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResource(PathMetadata metadata) {
        super(Resource.class, metadata);
    }

}

