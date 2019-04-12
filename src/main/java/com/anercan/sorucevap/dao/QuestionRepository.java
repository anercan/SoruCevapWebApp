package com.anercan.sorucevap.dao;

import com.anercan.sorucevap.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByUserId(Long userId);

}
