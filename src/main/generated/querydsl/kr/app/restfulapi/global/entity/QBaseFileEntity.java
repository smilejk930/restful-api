package kr.app.restfulapi.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseFileEntity is a Querydsl query type for BaseFileEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseFileEntity extends EntityPathBase<BaseFileEntity> {

    private static final long serialVersionUID = 880763737L;

    public static final QBaseFileEntity baseFileEntity = new QBaseFileEntity("baseFileEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath deleteAt = createString("deleteAt");

    public final NumberPath<Long> dwldCo = createNumber("dwldCo", Long.class);

    public final StringPath fileExtsnNm = createString("fileExtsnNm");

    public final StringPath fileGroupNm = createString("fileGroupNm");

    public final StringPath fileId = createString("fileId");

    public final StringPath fileNm = createString("fileNm");

    public final StringPath fileSectValue = createString("fileSectValue");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> fileSn = createNumber("fileSn", Long.class);

    public final StringPath fileStreCours = createString("fileStreCours");

    public final StringPath fileStreNm = createString("fileStreNm");

    public final StringPath fileSynchrnCode = createString("fileSynchrnCode");

    public final DateTimePath<java.time.LocalDateTime> fileSynchrnDt = createDateTime("fileSynchrnDt", java.time.LocalDateTime.class);

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    public final StringPath refrnId = createString("refrnId");

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

    public QBaseFileEntity(String variable) {
        super(BaseFileEntity.class, forVariable(variable));
    }

    public QBaseFileEntity(Path<? extends BaseFileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseFileEntity(PathMetadata metadata) {
        super(BaseFileEntity.class, metadata);
    }

}

