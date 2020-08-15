package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.dto.QuestionDto;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.mapper.QuestionMapper;
import com.anercan.sorucevap.resource.QuestionResource;
import com.anercan.sorucevap.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService extends AbstractEntityService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    BaseRepository getRepository() { return questionRepository; }

    public ServiceResult<QuestionResource> getByIdWithAnswers(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()) {
            QuestionResource questionResource = QuestionMapper.mapQuestionWithAnswers(questionOptional.get());
            log.info("Soru id ile getirildi.id:{}", id);
            return createServiceResult(questionResource);
        }
        log.info("Soru id bulunamadı.id:{}", id);
        return createFailResult();
    }

    public ServiceResult<List<Question>> getQuestionsByCategoryId(Long id) {
        if (!questionRepository.getListByCategory(id).isEmpty()) {
            return createServiceResult(questionRepository.getListByCategory(id));
        }
        return createFailResult();
    }

    public ServiceResult<Boolean> createQuestion(QuestionDto questionDto) {
        User user = userRepository.findById(questionDto.getUserDto().getId()).get(); //question ve answer olusturuldugunda event fırlat boylece soruya cevap geldiginde mail atma islemi yap
        if (user != null && user.getQuestionStatus() > 0) {
            Question question = new Question();
            question.setUser(user);
            question.setTitle(questionDto.getTitle());
            question.setContent(questionDto.getContent());
            user.setQuestionStatus(user.getQuestionStatus() - 1);
            log.info("Soru oluşturuldu.Soru:{}", question);
            questionRepository.save(question);

            return createServiceResult(Boolean.TRUE);
        }
        log.info("Soru Hakkı ya da user yok.user:{}", user);
        return createFailResult();
    }

    public ServiceResult<Boolean> deleteQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).get();
        if (question.getAnswer().size() > 50) {
            log.info("Soru silinme talebi oluşturuldu.Soru:{}", question); //todo talep
            return createFailResult();
        }
        log.info("Soru silindi.Soru:{}", question);
        questionRepository.delete(question);
        question.getUser().setQuestionStatus(question.getUser().getQuestionStatus() - 1);
        return createFailResult();
    }

    public ServiceResult<Boolean> addToFollowList(QuestionDto questionDto) {
        Optional<User> user = userRepository.findById(questionDto.getUserDto().getId());
        Optional<Question> question = questionRepository.findById(questionDto.getId());

        if (user.isPresent() || question.isPresent()) {
            log.error("Question cant added followList.User or Question Empty");
            return createFailResult();
        }

        user.get().getQuestionFollow().add(question.get());
        userRepository.save(user.get());

        return createServiceResult(Boolean.TRUE);
    }

}
