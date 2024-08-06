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
    public final NumberPath<Long> dwldCo = _super.dwldCo;

    //inherited
    public final StringPath fileExtsnNm = _super.fileExtsnNm;

    //inherited
    public final StringPath fileGroupNm = _super.fileGroupNm;

    //inherited
    public final StringPath fileId = _super.fileId;

    //inherited
    public final StringPath fileNm = _super.fileNm;

    //inherited
    public final StringPath fileSectValue = _super.fileSectValue;

    //inherited
    public final NumberPath<Long> fileSize = _super.fileSize;

    //inherited
    public final NumberPath<Long> fileSn = _super.fileSn;

    //inherited
    public final StringPath fileStreCours = _super.fileStreCours;

    //inherited
    public final StringPath fileStreNm = _super.fileStreNm;

    //inherited
    public final StringPath fileSynchrnCode = _super.fileSynchrnCode;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> fileSynchrnDt = _super.fileSynchrnDt;

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    //inherited
    public final StringPath refrnId = _super.refrnId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

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

