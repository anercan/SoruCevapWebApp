package com.anercan.sorucevap.dao;

import com.anercan.sorucevap.entity.Question;

public interface QuestionRepository extends BaseRepository<Question, Long> {

    Question findByUserId(Long userId);

}
