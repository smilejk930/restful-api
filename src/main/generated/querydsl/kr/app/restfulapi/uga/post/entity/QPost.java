package kr.app.restfulapi.uga.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 395013189L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final kr.app.restfulapi.uga.common.entity.QBaseEntity _super = new kr.app.restfulapi.uga.common.entity.QBaseEntity(this);

    public final StringPath cn = createString("cn");

    public final StringPath deleteAt = createString("deleteAt");

    //inherited
    public final StringPath lastServerNm = _super.lastServerNm;

    public final StringPath postId = createString("postId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registDt = _super.registDt;

    //inherited
    public final StringPath registerId = _super.registerId;

    //inherited
    public final StringPath registServerNm = _super.registServerNm;

    public final StringPath sj = createString("sj");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDt = _super.updtDt;

    //inherited
    public final StringPath updusrId = _super.updusrId;

    public final kr.app.restfulapi.uga.user.entity.QUser user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.app.restfulapi.uga.user.entity.QUser(forProperty("user")) : null;
    }

}

