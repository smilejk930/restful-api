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

    public final NumberPath<Long> dwnldCnt = createNumber("dwnldCnt", Long.class);

    public final StringPath fileClsfNm = createString("fileClsfNm");

    public final StringPath fileExtnNm = createString("fileExtnNm");

    public final EnumPath<kr.app.restfulapi.domain.common.file.util.FileGroupNmType> fileGroupNm = createEnum("fileGroupNm", kr.app.restfulapi.domain.common.file.util.FileGroupNmType.class);

    public final StringPath fileNm = createString("fileNm");

    public final NumberPath<Long> fileSeq = createNumber("fileSeq", Long.class);

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileStrgPath = createString("fileStrgPath");

    public final EnumPath<kr.app.restfulapi.domain.common.file.util.FileSyncType> fileSyncCd = createEnum("fileSyncCd", kr.app.restfulapi.domain.common.file.util.FileSyncType.class);

    public final DateTimePath<java.time.LocalDateTime> fileSyncDt = createDateTime("fileSyncDt", java.time.LocalDateTime.class);

    public final StringPath fileTsid = createString("fileTsid");

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

    public final StringPath rfrncTsid = createString("rfrncTsid");

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final StringPath strgFileNm = createString("strgFileNm");

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

