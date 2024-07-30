package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseServerEntity is a Querydsl query type for BaseServerEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseServerEntity extends EntityPathBase<BaseServerEntity> {

    private static final long serialVersionUID = 884710752L;

    public static final QBaseServerEntity baseServerEntity = new QBaseServerEntity("baseServerEntity");

    public final StringPath lastSrvrNm = createString("lastSrvrNm");

    public final StringPath regSrvrNm = createString("regSrvrNm");

    public QBaseServerEntity(String variable) {
        super(BaseServerEntity.class, forVariable(variable));
    }

    public QBaseServerEntity(Path<? extends BaseServerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseServerEntity(PathMetadata metadata) {
        super(BaseServerEntity.class, metadata);
    }

}

