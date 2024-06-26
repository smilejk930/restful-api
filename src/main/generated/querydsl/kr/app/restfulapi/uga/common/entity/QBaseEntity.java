package kr.app.restfulapi.uga.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 1143170180L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final StringPath lastServerNm = createString("lastServerNm");

    public final DateTimePath<java.time.LocalDateTime> registDt = createDateTime("registDt", java.time.LocalDateTime.class);

    public final StringPath registerId = createString("registerId");

    public final StringPath registServerNm = createString("registServerNm");

    public final DateTimePath<java.time.LocalDateTime> updtDt = createDateTime("updtDt", java.time.LocalDateTime.class);

    public final StringPath updusrId = createString("updusrId");

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

