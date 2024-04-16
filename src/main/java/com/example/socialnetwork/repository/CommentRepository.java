package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Comment;
import com.example.socialnetwork.entity.Comment_;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.Post_;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private final Entityql entityql;
    private final NativeSql nativeSql;

    public CommentRepository(Entityql entityql, NativeSql nativeSql) {
        this.entityql = entityql;
        this.nativeSql = nativeSql;
    }

    public Result<Comment> save(Comment comment) {
        var c = new Comment_();

        return entityql.insert(c, comment).execute();
    }
    public List<Comment> findByPost(String postId) {
        var c = new Comment_();

        return entityql.from(c).where(a -> a.eq(c.postId, postId)).fetch();
    }

    public Comment findById(String commentId) {
        var c = new Comment_();

        return entityql.from(c).where(a -> a.eq(c.id, commentId)).fetchOne();
    }

    public Result<Comment> update(Comment comment) {
        var c = new Comment_();

        return entityql.update(c, comment).execute();
    }

    public void delete(String commentId) {
        var c = new Comment_();

        int count = nativeSql.delete(c).where(e -> e.eq(c.id, commentId)).execute();
    }

}
