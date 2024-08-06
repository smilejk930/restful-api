package kr.app.restfulapi.domain.sample.post.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostFile is a Querydsl query type for PostFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostFile extends EntityPathBase<PostFile> {

    private static final long serialVersionUID = -1379767936L;

    public static final QPostFile postFile = new QPostFile("postFile");

    public final kr.app.restfulapi.domain.common.file.entity.QBaseFileEntity _super = new kr.app.restfulapi.domain.common.file.entity.QBaseFileEntity(this);

    //inherited
    public final StringPath delYn = _super.delYn;

    //inherited
    public final NumberPath<Long> dwnldCnt = _super.dwnldCnt;

    //inherited
    public final StringPath fileClsfNm = _super.fileClsfNm;

    //inherited
    public final StringPath fileExtnNm = _super.fileExtnNm;

    //inherited
    public final StringPath fileGroupNm = _super.fileGroupNm;

    //inherited
    public final StringPath fileNm = _super.fileNm;

    //inherited
    public final NumberPath<Long> fileSeq = _super.fileSeq;

    //inherited
    public final NumberPath<Long> fileSize = _super.fileSize;

    //inherited
    public final StringPath fileStreCours = _super.fileStreCours;

    //inherited
    public final StringPath fileSyncCd = _super.fileSyncCd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> fileSyncDt = _super.fileSyncDt;

    //inherited
    public final StringPath fileTsid = _super.fileTsid;

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
    public final StringPath rfrncTsid = _super.rfrncTsid;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    //inherited
    public final StringPath strgFileNm = _super.strgFileNm;

    public QPostFile(String variable) {
        super(PostFile.class, forVariable(variable));
    }

    public QPostFile(Path<? extends PostFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostFile(PathMetadata metadata) {
        super(PostFile.class, metadata);
    }

}

