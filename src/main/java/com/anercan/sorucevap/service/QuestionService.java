package com.anercan.sorucevap.service;

import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.client.dto.QuestionDto;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.mapper.QuestionMapper;
import com.anercan.sorucevap.client.resource.QuestionResource;
import com.anercan.sorucevap.client.resource.ServiceResult;
import com.anercan.sorucevap.enums.QuestionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService extends AbstractEntityService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserService userService;

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
        User user = userService.getCurrentUser();
        if (user != null && user.getQuestionStatus() > 0) {
            Question question = new Question();
            question.setUser(user);
            question.setTitle(questionDto.getTitle());
            //todo categorisini setle belki uygulama kalkarken categoriler static mape alınabilir
            question.setContent(questionDto.getContent());
            user.setQuestionStatus(user.getQuestionStatus() - 1);
            question.setStatus(QuestionStatus.CREATED);
            save(question);
            log.info("Soru oluşturuldu.Soru:{}", question.toString());
            //todo event
            //todo spam kontrol ?
            return createServiceResult(Boolean.TRUE);
        }
        log.info("Soru Hakkı ya da user yok.user:{}", user);
        return createFailResult();
    }

    public ServiceResult<Boolean> deleteQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).get();
        if (question.getAnswer().size() > PropertyUtil.getIntegerValue("app.common.deleteQuestion.answerLimit",10)) {
            log.info("Soru silinme talebi oluşturuldu.Soru:{}", question); //todo talep
            return createServiceResult(Boolean.TRUE,PropertyUtil.getClientProp("text.delete.request"));
        }
        log.info("Soru silindi.Soru:{}", question);
        questionRepository.delete(question);
        question.getUser().setQuestionStatus(question.getUser().getQuestionStatus() - 1);
        return createFailResult();
    }

    public ServiceResult<Boolean> addToFollowList(QuestionDto questionDto) {
        User user = userService.getCurrentUser();
        Optional<Question> question = questionRepository.findById(questionDto.getId());

        if (user == null || !question.isPresent()) {
            log.error("Question cant added followList.User or Question Empty");
            return createFailResult();
        }

        user.getQuestionFollow().add(question.get());
        userService.save(user);

        return createServiceResult(Boolean.TRUE);
    }

}
