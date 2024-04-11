package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.User;
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

    public Optional<User> findByEmail(String email) {
        return Optional.of(new User());
    }
}
