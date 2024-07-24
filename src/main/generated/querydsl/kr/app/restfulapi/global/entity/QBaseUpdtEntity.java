package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseUpdtEntity is a Querydsl query type for BaseUpdtEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseUpdtEntity extends EntityPathBase<BaseUpdtEntity> {

    private static final long serialVersionUID = 1830821640L;

    public static final QBaseUpdtEntity baseUpdtEntity = new QBaseUpdtEntity("baseUpdtEntity");

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

    public QBaseUpdtEntity(String variable) {
        super(BaseUpdtEntity.class, forVariable(variable));
    }

    public QBaseUpdtEntity(Path<? extends BaseUpdtEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseUpdtEntity(PathMetadata metadata) {
        super(BaseUpdtEntity.class, metadata);
    }

}

