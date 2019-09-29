package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService {

    //todo baseserviceden al repoları buraya

    @Override
    public Optional<Question> getById(Long id) {
        logger.info("Soru id ile getirildi.id:{}",id);
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(Question question) {
        User user = userRepository.findById(question.getUser().getId()).get();
        if(user.getQuestionStatus()>0) {
            user.setQuestionStatus(user.getQuestionStatus() - 1);
            user.setQuestionCount(user.getQuestionCount() + 1);
            question.setDate(date);
            question.setUser(user);
            logger.info("Soru oluşturuldu.Soru:{}",question.getId());
            return questionRepository.save(question);
        }
        else
            logger.info("Soru Hakkı Yok.");
            return null;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.findById(id).get().getUser().setQuestionCount(questionRepository.findById(id).get().getUser().getQuestionCount()-1);
        if(questionRepository.findById(id).get().getAnswerCount()==0)
            questionRepository.findById(id).get().getUser().setQuestionStatus(questionRepository.findById(id).get().getUser().getQuestionStatus()+1);
        else
            questionRepository.findById(id).get().getUser().setQuestionStatus(questionRepository.findById(id).get().getUser().getQuestionStatus()-1);
        questionRepository.delete(questionRepository.findById(id).get());
        logger.info("Soru silindi.id:{}",id);
    }
}
