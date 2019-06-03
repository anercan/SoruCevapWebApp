package com.anercan.sorucevap.dao;


import com.anercan.sorucevap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

     User findByUsername(String userName);
     User findByMail(String mail);

}