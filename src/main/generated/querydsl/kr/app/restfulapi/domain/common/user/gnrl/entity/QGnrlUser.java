package kr.app.restfulapi.domain.common.user.gnrl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGnrlUser is a Querydsl query type for GnrlUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGnrlUser extends EntityPathBase<GnrlUser> {

    private static final long serialVersionUID = -249367381L;

    public static final QGnrlUser gnrlUser = new QGnrlUser("gnrlUser");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    public final StringPath ahrztKeyNo = createString("ahrztKeyNo");

    public final StringPath bzentyMngrYn = createString("bzentyMngrYn");

    public final StringPath bzentyTsid = createString("bzentyTsid");

    public final StringPath cardNo = createString("cardNo");

    public final StringPath ciNo = createString("ciNo");

    public final StringPath diNo = createString("diNo");

    public final StringPath emlAddr = createString("emlAddr");

    public final StringPath emlRcptnAgreYmd = createString("emlRcptnAgreYmd");

    public final StringPath emlRcptnAgreYn = createString("emlRcptnAgreYn");

    public final StringPath fxno = createString("fxno");

    public final DateTimePath<java.time.LocalDateTime> joinDt = createDateTime("joinDt", java.time.LocalDateTime.class);

    public final StringPath katalkRcptnAgreYmd = createString("katalkRcptnAgreYmd");

    public final StringPath katalkRcptnAgreYn = createString("katalkRcptnAgreYn");

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    public final DateTimePath<java.time.LocalDateTime> lgnDt = createDateTime("lgnDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> lgnFailNmtm = createNumber("lgnFailNmtm", Integer.class);

    public final StringPath lgnId = createString("lgnId");

    public final DateTimePath<java.time.LocalDateTime> lgnLckDt = createDateTime("lgnLckDt", java.time.LocalDateTime.class);

    public final StringPath lgnLckYn = createString("lgnLckYn");

    public final StringPath ltrMsgRcptnAgreYmd = createString("ltrMsgRcptnAgreYmd");

    public final StringPath ltrMsgRcptnAgreYn = createString("ltrMsgRcptnAgreYn");

    public final StringPath mbtlCertKeyNo = createString("mbtlCertKeyNo");

    public final StringPath mbtlnum = createString("mbtlnum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    public final StringPath prvcUtlzAgreYmd = createString("prvcUtlzAgreYmd");

    public final StringPath prvcUtlzAgreYn = createString("prvcUtlzAgreYn");

    public final StringPath pswd = createString("pswd");

    public final StringPath pswdChgYmd = createString("pswdChgYmd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final StringPath telno = createString("telno");

    public final EnumPath<kr.app.restfulapi.domain.common.user.gnrl.util.UserAcntSttsType> userAcntSttsCd = createEnum("userAcntSttsCd", kr.app.restfulapi.domain.common.user.gnrl.util.UserAcntSttsType.class);

    public final SetPath<UserAuthrt, QUserAuthrt> userAuthrts = this.<UserAuthrt, QUserAuthrt>createSet("userAuthrts", UserAuthrt.class, QUserAuthrt.class, PathInits.DIRECT2);

    public final StringPath userNm = createString("userNm");

    public final StringPath userTsid = createString("userTsid");

    public QGnrlUser(String variable) {
        super(GnrlUser.class, forVariable(variable));
    }

    public QGnrlUser(Path<? extends GnrlUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGnrlUser(PathMetadata metadata) {
        super(GnrlUser.class, metadata);
    }

}

