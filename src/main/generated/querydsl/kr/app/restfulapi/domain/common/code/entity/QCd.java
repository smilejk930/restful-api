package kr.app.restfulapi.domain.common.code.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCd is a Querydsl query type for Cd
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCd extends EntityPathBase<Cd> {

    private static final long serialVersionUID = -1687312371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCd cd = new QCd("cd");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    public final StringPath cdExpln = createString("cdExpln");

    public final QCdGroup cdGroup;

    public final StringPath cdGroupNm = createString("cdGroupNm");

    public final StringPath cdKornNm = createString("cdKornNm");

    public final StringPath cdNm = createString("cdNm");

    public final NumberPath<Integer> cdSeq = createNumber("cdSeq", Integer.class);

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
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final StringPath upCdNm = createString("upCdNm");

    public final StringPath useYn = createString("useYn");

    public QCd(String variable) {
        this(Cd.class, forVariable(variable), INITS);
    }

    public QCd(Path<? extends Cd> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCd(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCd(PathMetadata metadata, PathInits inits) {
        this(Cd.class, metadata, inits);
    }

    public QCd(Class<? extends Cd> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cdGroup = inits.isInitialized("cdGroup") ? new QCdGroup(forProperty("cdGroup")) : null;
    }

}

