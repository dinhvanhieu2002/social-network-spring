package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Role;
import com.example.socialnetwork.entity.Role_;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.entity.User_;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {
    private final Entityql entityql;

    public RoleRepository(Entityql entityql) {
        this.entityql = entityql;
    }
    public Role findById(Integer id) {
        var c = new Role_();

        return entityql.from(c).where(r -> r.eq(c.id, id)).fetchOne();
    }
}
