package kr.app.restfulapi.domain.sample.edu.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1719666477L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<kr.app.restfulapi.domain.sample.edu.user.post.Post, kr.app.restfulapi.domain.sample.edu.user.post.QPost> posts = this.<kr.app.restfulapi.domain.sample.edu.user.post.Post, kr.app.restfulapi.domain.sample.edu.user.post.QPost>createList("posts", kr.app.restfulapi.domain.sample.edu.user.post.Post.class, kr.app.restfulapi.domain.sample.edu.user.post.QPost.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

