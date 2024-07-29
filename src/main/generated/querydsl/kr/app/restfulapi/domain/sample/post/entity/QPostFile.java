package kr.app.restfulapi.domain.sample.post.entity;

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

    private static final long serialVersionUID = -1963404484L;

    public static final QPostFile postFile = new QPostFile("postFile");

    public final kr.app.restfulapi.global.entity.QBaseFileEntity _super = new kr.app.restfulapi.global.entity.QBaseFileEntity(this);

    //inherited
    public final StringPath deleteAt = _super.deleteAt;

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
    public final StringPath lastServerNm = _super.lastServerNm;

    //inherited
    public final StringPath refrnId = _super.refrnId;

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

