package com.anercan.sorucevap.dao;


import com.anercan.sorucevap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

     User findByUsername(String userName);

     Optional<User> findById(Long id);

     User findByMail(String mail);
}