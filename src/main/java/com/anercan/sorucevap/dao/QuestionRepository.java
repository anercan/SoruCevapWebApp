package com.anercan.sorucevap.dao;

import com.anercan.sorucevap.entity.Category;
import com.anercan.sorucevap.entity.Question;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends BaseRepository<Question, Long> {

    @Query(value = "select * from questions where id in (SELECT question_id FROM category_relations WHERE category_id=:id)",nativeQuery = true)
    List<Question> getListByCategory(Long id);


}
