package com.anercan.sorucevap.dao;

import com.anercan.sorucevap.entity.Category;
import com.anercan.sorucevap.entity.Question;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuestionRepository extends BaseRepository<Question, Long> {

    @Query(value = "SELECT question_id FROM category_relations WHERE category_id=:id",nativeQuery = true)
    Collection<Question> getListByCategory(Long id);


}
