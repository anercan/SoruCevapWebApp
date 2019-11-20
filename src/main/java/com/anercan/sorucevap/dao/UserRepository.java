package com.anercan.sorucevap.dao;


import com.anercan.sorucevap.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByMail(String mail);

}