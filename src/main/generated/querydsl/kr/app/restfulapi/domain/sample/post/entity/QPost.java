package kr.app.restfulapi.domain.sample.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -850576992L;

    public static final QPost post = new QPost("post");

    public final kr.app.restfulapi.global.entity.QBaseAuditingEntity _super = new kr.app.restfulapi.global.entity.QBaseAuditingEntity(this);

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

    public final DateTimePath<java.time.LocalDateTime> sbmsnDt = createDateTime("sbmsnDt", java.time.LocalDateTime.class);

    public final StringPath sbmsnYn = createString("sbmsnYn");

    public final StringPath sj = createString("sj");

    public final NumberPath<Integer> telgmLen = createNumber("telgmLen", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDt = _super.updtDt;

    //inherited
    public final StringPath updusrId = _super.updusrId;

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

