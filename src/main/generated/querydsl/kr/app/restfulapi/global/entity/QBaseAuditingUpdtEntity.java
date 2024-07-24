package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingUpdtEntity is a Querydsl query type for BaseAuditingUpdtEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingUpdtEntity extends EntityPathBase<BaseAuditingUpdtEntity> {

    private static final long serialVersionUID = 1214703247L;

    public static final QBaseAuditingUpdtEntity baseAuditingUpdtEntity = new QBaseAuditingUpdtEntity("baseAuditingUpdtEntity");

    public final QBaseAuditingRegistEntity _super = new QBaseAuditingRegistEntity(this);

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registDt = _super.registDt;

    //inherited
    public final StringPath registerId = _super.registerId;

    //inherited
    public final StringPath registServerNm = _super.registServerNm;

    public final DateTimePath<java.time.LocalDateTime> updtDt = createDateTime("updtDt", java.time.LocalDateTime.class);

    public final StringPath updusrId = createString("updusrId");

    public QBaseAuditingUpdtEntity(String variable) {
        super(BaseAuditingUpdtEntity.class, forVariable(variable));
    }

    public QBaseAuditingUpdtEntity(Path<? extends BaseAuditingUpdtEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingUpdtEntity(PathMetadata metadata) {
        super(BaseAuditingUpdtEntity.class, metadata);
    }

}

