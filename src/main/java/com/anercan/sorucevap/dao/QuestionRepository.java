package com.anercan.sorucevap.dao;

import com.anercan.sorucevap.entity.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends BaseRepository<Question, Long> {

    @Query(value = "select * from questions where id in (SELECT question_id FROM category_relations WHERE category_id=:id)", nativeQuery = true)
    List<Question> getListByCategory(Long id);

    @Query(value = "SELECT count(*) FROM category_relations WHERE category_id=:id", nativeQuery = true)
    int getNumberByCategory(Long id);


}
