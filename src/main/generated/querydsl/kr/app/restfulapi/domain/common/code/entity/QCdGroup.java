package kr.app.restfulapi.domain.common.code.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCdGroup is a Querydsl query type for CdGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCdGroup extends EntityPathBase<CdGroup> {

    private static final long serialVersionUID = 1227382386L;

    public static final QCdGroup cdGroup = new QCdGroup("cdGroup");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    public final StringPath cdExpln = createString("cdExpln");

    public final StringPath cdGroupNm = createString("cdGroupNm");

    public final StringPath cdKornNm = createString("cdKornNm");

    public final SetPath<Cd, QCd> cds = this.<Cd, QCd>createSet("cds", Cd.class, QCd.class, PathInits.DIRECT2);

    public final StringPath cdSeNm = createString("cdSeNm");

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

    public final StringPath useYn = createString("useYn");

    public QCdGroup(String variable) {
        super(CdGroup.class, forVariable(variable));
    }

    public QCdGroup(Path<? extends CdGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCdGroup(PathMetadata metadata) {
        super(CdGroup.class, metadata);
    }

}

