package kr.app.restfulapi.domain.common.file.entity;

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

    private static final long serialVersionUID = 351151659L;

    public static final QBaseFileEntity baseFileEntity = new QBaseFileEntity("baseFileEntity");

    public final kr.app.restfulapi.global.entity.QBaseEntity _super = new kr.app.restfulapi.global.entity.QBaseEntity(this);

    public final StringPath delYn = createString("delYn");

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
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    public final StringPath refrnId = createString("refrnId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

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

