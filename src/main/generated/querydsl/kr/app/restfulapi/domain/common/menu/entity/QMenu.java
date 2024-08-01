package kr.app.restfulapi.domain.common.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 2124628605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

    public final ListPath<Menu, QMenu> children = this.<Menu, QMenu>createList("children", Menu.class, QMenu.class, PathInits.DIRECT2);

    public final StringPath httpDmndMethNm = createString("httpDmndMethNm");

    //inherited
    public final StringPath lastSrvrNm = _super.lastSrvrNm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfcnDt = _super.mdfcnDt;

    //inherited
    public final StringPath mdfrTsid = _super.mdfrTsid;

    public final EnumPath<kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType> menuAcsAuthrtCd = createEnum("menuAcsAuthrtCd", kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType.class);

    public final SetPath<MenuAuthrt, QMenuAuthrt> menuAuthrts = this.<MenuAuthrt, QMenuAuthrt>createSet("menuAuthrts", MenuAuthrt.class, QMenuAuthrt.class, PathInits.DIRECT2);

    public final StringPath menuExpln = createString("menuExpln");

    public final StringPath menuGroupCd = createString("menuGroupCd");

    public final StringPath menuNm = createString("menuNm");

    public final NumberPath<Long> menuSeq = createNumber("menuSeq", Long.class);

    public final StringPath menuTsid = createString("menuTsid");

    public final EnumPath<kr.app.restfulapi.domain.common.menu.util.MenuType> menuTypeCd = createEnum("menuTypeCd", kr.app.restfulapi.domain.common.menu.util.MenuType.class);

    public final StringPath npagYn = createString("npagYn");

    public final QMenu parent;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regSrvrNm = _super.regSrvrNm;

    //inherited
    public final StringPath rgtrTsid = _super.rgtrTsid;

    public final StringPath upMenuTsid = createString("upMenuTsid");

    public final StringPath urlAddr = createString("urlAddr");

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QMenu(forProperty("parent"), inits.get("parent")) : null;
    }

}

