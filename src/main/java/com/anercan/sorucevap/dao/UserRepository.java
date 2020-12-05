package com.anercan.sorucevap.dao;


import com.anercan.sorucevap.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    @Query(value = "select u from User u where u.username = ?1 or u.mail= ?1")
    Optional<User> findByUsernameOrMail(String userNameOrMail);

    Optional<User> findByMail(String mail);

}