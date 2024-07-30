package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingMdfcnEntity is a Querydsl query type for BaseAuditingMdfcnEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingMdfcnEntity extends EntityPathBase<BaseAuditingMdfcnEntity> {

    private static final long serialVersionUID = 520945820L;

    public static final QBaseAuditingMdfcnEntity baseAuditingMdfcnEntity = new QBaseAuditingMdfcnEntity("baseAuditingMdfcnEntity");

    public final QBaseAuditingRegEntity _super = new QBaseAuditingRegEntity(this);

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = createDateTime("mdfcnDt", java.time.LocalDateTime.class);

    public final StringPath mdfrTsid = createString("mdfrTsid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public QBaseAuditingMdfcnEntity(String variable) {
        super(BaseAuditingMdfcnEntity.class, forVariable(variable));
    }

    public QBaseAuditingMdfcnEntity(Path<? extends BaseAuditingMdfcnEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingMdfcnEntity(PathMetadata metadata) {
        super(BaseAuditingMdfcnEntity.class, metadata);
    }

}

