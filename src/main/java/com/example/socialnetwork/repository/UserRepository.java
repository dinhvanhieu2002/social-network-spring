package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.entity.User_;
import com.example.socialnetwork.payloads.UserDto;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Entityql entityql;

    public UserRepository(Entityql entityql) {
        this.entityql = entityql;
    }
    public User save(User user) {
        var c = new User_();

        var savedCustomer = entityql.insert(c, user).execute();
        return savedCustomer.getEntity();
    }

    public User findByEmail(String email) {
        var c = new User_();

        return entityql.from(c).where(a -> a.eq(c.email, email)).fetchOne();
    }

    public User findById(String id) {
        var c = new User_();

        return entityql.from(c).where(a -> a.eq(c.id, id)).fetchOne();
    }
}
