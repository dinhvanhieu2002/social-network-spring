package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.Post_;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    private final Entityql entityql;
    private final NativeSql nativeSql;

    public PostRepository(Entityql entityql, NativeSql nativeSql) {
        this.entityql = entityql;
        this.nativeSql = nativeSql;
    }

    public Result<Post> save(Post post) {
        var c = new Post_();

        return entityql.insert(c, post).execute();
    }
    public List<Post> findByUser(String userId) {
        var c = new Post_();

        return entityql.from(c).where(a -> a.eq(c.userId, userId)).fetch();
    }

    public Post findById(String postId) {
        var c = new Post_();

        return entityql.from(c).where(a -> a.eq(c.id, postId)).fetchOne();
    }

    public Result<Post> update(Post post) {
        var c = new Post_();

        return entityql.update(c, post).execute();
    }

    public void delete(String postId) {
        var c = new Post_();

        int count = nativeSql.delete(c).where(e -> e.eq(c.id, postId)).execute();
    }

}
