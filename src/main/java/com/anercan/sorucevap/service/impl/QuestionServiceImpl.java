package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.dto.QuestionDto;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public JsonResponse<Question> getById(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            logger.info("Soru id ile getirildi.id:{}", id);
            return new JsonResponse(questionOptional.get());
        }
        logger.info("Soru id bulunamadı.id:{}", id);
        return createFailResult();
    }

    @Override
    public JsonResponse<List<Question>> getQuestionsByCategoryId(Long id) {
        if (!questionRepository.getListByCategory(id).isEmpty()) {
            return new JsonResponse<>(questionRepository.getListByCategory(id));
        }
        return createFailResult();
    }

    @Override
    public JsonResponse<Boolean> createQuestion(QuestionDto questionDto) {
        User user = userRepository.findById(questionDto.getUserDto().getId()).get();
        if (user != null && user.getQuestionStatus() > 0) {
            Question question = new Question();
            question.setUser(user);
            question.setTitle(questionDto.getTitle());
            question.setContent(questionDto.getContent());
            user.setQuestionStatus(user.getQuestionStatus() - 1); //Soru hakkı indirildi
            question.setDate(date);
            logger.info("Soru oluşturuldu.Soru:{}", question);
            questionRepository.save(question);

            return new JsonResponse(true);
        }
        logger.info("Soru Hakkı ya da user yok.user:{}", user);
        return createFailResult();
    }

    @Override
    public JsonResponse<Boolean> deleteQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).get();
        if (question.getAnswer().size() > 50) {
            logger.info("Soru silinme talebi oluşturuldu.Soru:{}", question); //todo talep
            return createFailResult();
        }
        logger.info("Soru silindi.Soru:{}", question);
        questionRepository.delete(question);
        question.getUser().setQuestionStatus(question.getUser().getQuestionStatus() - 1);
        return createFailResult();
    }

    @Override
    public JsonResponse<Boolean> addToFollowList(QuestionDto questionDto) {
        Optional<User> user = userRepository.findById(questionDto.getUserDto().getId());
        Optional<Question> question = questionRepository.findById(questionDto.getId());

        if (user.isPresent() || question.isPresent()) {
            logger.error("Question cant added followList.User or Question Empty");
            return createFailResult();
        }

        user.get().getQuestionFollow().add(question.get());
        userRepository.save(user.get());

        return new JsonResponse<>(Boolean.TRUE);
    }

}
