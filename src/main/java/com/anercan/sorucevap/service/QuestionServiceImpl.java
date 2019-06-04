package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Question> getById(Long id) {
        logger.info("Soru id ile getirildi.id:{}",id);
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(Question question) {
        question.getUser().setQuestionStatus(question.getUser().getQuestionStatus()-1);
        question.getUser().setQuestionCount(question.getUser().getAnswerCount()+1);
        question.setDate(date);
        logger.info("Soru oluşturuldu.Soru:{}",question);
        return questionRepository.save(question);
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
