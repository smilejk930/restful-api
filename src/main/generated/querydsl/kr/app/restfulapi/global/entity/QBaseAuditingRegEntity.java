package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingRegEntity is a Querydsl query type for BaseAuditingRegEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingRegEntity extends EntityPathBase<BaseAuditingRegEntity> {

    private static final long serialVersionUID = -1409153258L;

    public static final QBaseAuditingRegEntity baseAuditingRegEntity = new QBaseAuditingRegEntity("baseAuditingRegEntity");

    public final QBaseServerEntity _super = new QBaseServerEntity(this);

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    public final StringPath rgtrTsid = createString("rgtrTsid");

    public QBaseAuditingRegEntity(String variable) {
        super(BaseAuditingRegEntity.class, forVariable(variable));
    }

    public QBaseAuditingRegEntity(Path<? extends BaseAuditingRegEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingRegEntity(PathMetadata metadata) {
        super(BaseAuditingRegEntity.class, metadata);
    }

}

