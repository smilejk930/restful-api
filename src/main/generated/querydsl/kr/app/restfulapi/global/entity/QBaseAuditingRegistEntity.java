package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingRegistEntity is a Querydsl query type for BaseAuditingRegistEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingRegistEntity extends EntityPathBase<BaseAuditingRegistEntity> {

    private static final long serialVersionUID = 1664640282L;

    public static final QBaseAuditingRegistEntity baseAuditingRegistEntity = new QBaseAuditingRegistEntity("baseAuditingRegistEntity");

    public final QBaseServerEntity _super = new QBaseServerEntity(this);

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    public final DateTimePath<java.time.LocalDateTime> registDt = createDateTime("registDt", java.time.LocalDateTime.class);

    public final StringPath registerId = createString("registerId");

    //inherited
    public final StringPath registServerNm = _super.registServerNm;

    public QBaseAuditingRegistEntity(String variable) {
        super(BaseAuditingRegistEntity.class, forVariable(variable));
    }

    public QBaseAuditingRegistEntity(Path<? extends BaseAuditingRegistEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingRegistEntity(PathMetadata metadata) {
        super(BaseAuditingRegistEntity.class, metadata);
    }

}

