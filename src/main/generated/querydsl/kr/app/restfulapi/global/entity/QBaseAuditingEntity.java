package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingEntity is a Querydsl query type for BaseAuditingEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingEntity extends EntityPathBase<BaseAuditingEntity> {

    private static final long serialVersionUID = -95768892L;

    public static final QBaseAuditingEntity baseAuditingEntity = new QBaseAuditingEntity("baseAuditingEntity");

    public final QBaseAuditingUpdtEntity _super = new QBaseAuditingUpdtEntity(this);

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registDt = _super.registDt;

    //inherited
    public final StringPath registerId = _super.registerId;

    //inherited
    public final StringPath registServerNm = _super.registServerNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDt = _super.updtDt;

    //inherited
    public final StringPath updusrId = _super.updusrId;

    public QBaseAuditingEntity(String variable) {
        super(BaseAuditingEntity.class, forVariable(variable));
    }

    public QBaseAuditingEntity(Path<? extends BaseAuditingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingEntity(PathMetadata metadata) {
        super(BaseAuditingEntity.class, metadata);
    }

}

