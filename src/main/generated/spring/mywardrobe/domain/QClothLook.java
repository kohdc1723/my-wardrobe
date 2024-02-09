package spring.mywardrobe.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClothLook is a Querydsl query type for ClothLook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothLook extends EntityPathBase<ClothLook> {

    private static final long serialVersionUID = -1933125004L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClothLook clothLook = new QClothLook("clothLook");

    public final QCloth cloth;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLook look;

    public QClothLook(String variable) {
        this(ClothLook.class, forVariable(variable), INITS);
    }

    public QClothLook(Path<? extends ClothLook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClothLook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClothLook(PathMetadata metadata, PathInits inits) {
        this(ClothLook.class, metadata, inits);
    }

    public QClothLook(Class<? extends ClothLook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cloth = inits.isInitialized("cloth") ? new QCloth(forProperty("cloth"), inits.get("cloth")) : null;
        this.look = inits.isInitialized("look") ? new QLook(forProperty("look"), inits.get("look")) : null;
    }

}

