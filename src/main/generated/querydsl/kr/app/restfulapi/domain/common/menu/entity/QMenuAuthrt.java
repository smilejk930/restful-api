package kr.app.restfulapi.domain.common.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuAuthrt is a Querydsl query type for MenuAuthrt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuAuthrt extends EntityPathBase<MenuAuthrt> {

    private static final long serialVersionUID = 1571208903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuAuthrt menuAuthrt = new QMenuAuthrt("menuAuthrt");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    public final QMenu menu;

    public final StringPath menuGroupCd = createString("menuGroupCd");

    public final StringPath menuTsid = createString("menuTsid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final EnumPath<kr.app.restfulapi.domain.common.user.gnrl.util.UserType> userTypeCd = createEnum("userTypeCd", kr.app.restfulapi.domain.common.user.gnrl.util.UserType.class);

    public QMenuAuthrt(String variable) {
        this(MenuAuthrt.class, forVariable(variable), INITS);
    }

    public QMenuAuthrt(Path<? extends MenuAuthrt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuAuthrt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuAuthrt(PathMetadata metadata, PathInits inits) {
        this(MenuAuthrt.class, metadata, inits);
    }

    public QMenuAuthrt(Class<? extends MenuAuthrt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
    }

}

