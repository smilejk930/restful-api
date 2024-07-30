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

    public final QBaseAuditingMdfcnEntity _super = new QBaseAuditingMdfcnEntity(this);

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

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

