package com.basic.myspringboot.repoository;

import com.basic.myspringboot.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User,Long> {
    List<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
