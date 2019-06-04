package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.AnswerRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl extends BaseService implements AnswerService  {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Answer> getById(Long id) {
        logger.info("Cevap id ile getirildi.id:{}",id);
        return answerRepository.findById(id);
    }

    @Override
    public Answer createAnswer(Answer answer) {
        answer.getUser().setAnswerCount(answer.getUser().getAnswerCount()+1);
        answer.setDate(date);
        logger.info("Cevap oluşturuldu.Cevap id:{}",answer.getId());
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.findById(id).get().getUser().setAnswerCount(answerRepository.findById(id).get().getUser().getAnswerCount()-1);
        answerRepository.delete(answerRepository.findById(id).get());
        logger.info("Cevap silindi.id:{}",id);
    }

    @Override
    public Answer likeAnswer(Long id) {
        Answer answer = answerRepository.findById(id).get();
        answer.setLikeCount(answer.getLikeCount()+1);
        if(!answer.isVerified()){
            if(answer.getLikeCount()-answer.getDislikeCount()>10) {
                answer.setVerified(true);
                answer.getUser().setQuestionStatus(answer.getUser().getQuestionStatus()+1);
            }
        }
        answer.getUser().setAnswerCount(answer.getUser().getAnswerCount()+1);
        logger.info("Cevap likelandi.Cevap id:{}",answer.getId());
        return answerRepository.save(answer);
    }

    @Override
    public Answer dislikeAnswer(Long id) {
        Answer answer = answerRepository.findById(id).get();
        answer.setDislikeCount(answer.getDislikeCount()+1);
        if(answer.isVerified()){
            if(answer.getLikeCount()-answer.getDislikeCount()<10) {
                answer.setVerified(false);
                answer.getUser().setQuestionStatus(answer.getUser().getQuestionStatus()-1);
            }
        }
        answer.getUser().setAnswerCount(answer.getUser().getAnswerCount()+1);
        logger.info("Cevap dislikelandi.Cevap id:{}",answer.getId());
        return answerRepository.save(answer);
    }

}
