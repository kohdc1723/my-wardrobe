package spring.mywardrobe.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCloth is a Querydsl query type for Cloth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCloth extends EntityPathBase<Cloth> {

    private static final long serialVersionUID = 266912309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCloth cloth = new QCloth("cloth");

    public final StringPath brand = createString("brand");

    public final QCollection collection;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final ListPath<Season, EnumPath<Season>> seasons = this.<Season, EnumPath<Season>>createList("seasons", Season.class, EnumPath.class, PathInits.DIRECT2);

    public final QUser user;

    public QCloth(String variable) {
        this(Cloth.class, forVariable(variable), INITS);
    }

    public QCloth(Path<? extends Cloth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCloth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCloth(PathMetadata metadata, PathInits inits) {
        this(Cloth.class, metadata, inits);
    }

    public QCloth(Class<? extends Cloth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.collection = inits.isInitialized("collection") ? new QCollection(forProperty("collection"), inits.get("collection")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

