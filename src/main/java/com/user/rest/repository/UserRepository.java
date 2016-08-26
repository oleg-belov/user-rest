package com.user.rest.repository;

import com.user.rest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sergey on 06.09.15.
 */
public interface UserRepository extends
        MongoRepository<User, String> {
}
