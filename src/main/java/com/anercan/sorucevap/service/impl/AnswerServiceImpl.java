package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl extends BaseService implements AnswerService {

    @Override
    public JsonResponse<List<Answer>> getAnswersByQuestionId(Long id) {
        return new JsonResponse(questionRepository.findById(id).get().getAnswer());
    }

    @Override
    public JsonResponse<Optional<Answer>> getById(Long id) {
        JsonResponse<Optional<Answer>> response = new JsonResponse<>();
        if(answerRepository.findById(id).isPresent()){
            logger.info("Cevap id ile getirildi.id:{}",id);
            return  new JsonResponse(answerRepository.findById(id));
        }
        else{
            logger.info("Girilen id uygun cevap yok");
            response.setMessage("Girilen id uygun cevap yok");
            response.setCode(-1);
            return response;
        }
    }

    @Override
    public JsonResponse<Answer> createAnswer(Answer answer) {
        JsonResponse<Answer> response = new JsonResponse<>();
       /* if(userRepository.findById(questionRepository.findById(answer.getQuestion().getId()).isPresent()){
        User user = userRepository.findById(answer.getUser().getId()).get();
        Question question = questionRepository.findById(answer.getQuestion().getId()).get();

        user.setAnswerCount(user.getAnswerCount()+1);
        answer.setDate(date);
        question.setAnswerCount(question.getAnswerCount()+1);
        answer.setUser(user);
        answer.setQuestion(question);

        logger.info("Cevap oluşturuldu.Cevap:{}",answer.getId());
        response.setValue(answerRepository.save(answer));
        }
        else{
            response.setCode(-1);
            response.setMessage("fail");
        }*/
        return response;
    }

    @Override
    public void deleteAnswer(Long id) {
      /*  answerRepository.findById(id).get().getUser().setAnswerCount(answerRepository.findById(id).get().getUser().getAnswerCount()-1);
        answerRepository.delete(answerRepository.findById(id).get());
        logger.info("Cevap silindi.id:{}",id);*/
    }

    @Override
    public JsonResponse<Answer> likeAnswer(Long id) {
        JsonResponse<Answer> response = new JsonResponse<>();
      /*  if(answerRepository.findById(id).isPresent()){
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
        response.setValue(answerRepository.save(answer));
        }
        else{
            response.setCode(-1);
            response.setMessage("fail");
        }*/
        return response;
    }

    @Override
    public JsonResponse<Answer> dislikeAnswer(Long id) {
        Answer answer = answerRepository.findById(id).get();
     /*   User user = userRepository.findById(answer.getUser().getId()).get();

        answer.setDislikeCount(answer.getDislikeCount()+1);
        if(answer.isVerified()){
            if(answer.getLikeCount()-answer.getDislikeCount()<10) {
                answer.setVerified(false);
                user.setQuestionStatus(user.getQuestionStatus()-1);
            }
        }
        user.setAnswerCount(user.getAnswerCount()+1);
        logger.info("Cevap dislikelandi.Cevap id:{}",answer.getId());*/
        return new JsonResponse(answerRepository.save(answer));
    }

}
