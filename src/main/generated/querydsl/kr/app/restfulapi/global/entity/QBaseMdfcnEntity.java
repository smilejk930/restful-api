package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseMdfcnEntity is a Querydsl query type for BaseMdfcnEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseMdfcnEntity extends EntityPathBase<BaseMdfcnEntity> {

    private static final long serialVersionUID = -1854220477L;

    public static final QBaseMdfcnEntity baseMdfcnEntity = new QBaseMdfcnEntity("baseMdfcnEntity");

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

    public QBaseMdfcnEntity(String variable) {
        super(BaseMdfcnEntity.class, forVariable(variable));
    }

    public QBaseMdfcnEntity(Path<? extends BaseMdfcnEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseMdfcnEntity(PathMetadata metadata) {
        super(BaseMdfcnEntity.class, metadata);
    }

}

