package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.AnswerRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.dto.AnswerDto;
import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl extends BaseService implements AnswerService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public JsonResponse<List<Answer>> getAnswersByQuestionId(Long id) {
        return new JsonResponse(questionRepository.findById(id).get().getAnswer());
    }

    @Override
    public JsonResponse<Answer> getById(Long id) {
        JsonResponse<Optional<Answer>> response = new JsonResponse<>();
        if (answerRepository.findById(id).isPresent()) {
            logger.info("Cevap id ile getirildi.id:{}", id);
            return new JsonResponse(answerRepository.findById(id).get());
        }
        logger.info("Girilen id uygun cevap yok.id:{}", id);
        return createFailResult();
    }

    @Override
    public JsonResponse<Boolean> createAnswer(AnswerDto answerDto) {
        /*Optional<Question> question = questionRepository.findById(answerDto.getQuestionDto().getId());
        if(question.isPresent()){
            new JsonResponse<>().setCode(-1);
        }*/
        Answer answer = new Answer();
        User owner = userRepository.findById(answerDto.getUserDto().getId()).get();
        Question question = questionRepository.findById(answerDto.getQuestionDto().getId()).get();
        answer.setQuestion(question);
        answer.setUser(owner);
        answer.setDate(date);
        answer.setContent(answerDto.getContent());
        logger.info("Cevap oluşturuldu.Cevap:{}", answer.getId());
        answerRepository.save(answer);
        return new JsonResponse<>(Boolean.TRUE);
    }

    @Override
    public JsonResponse<Boolean> deleteAnswer(AnswerDto answerDto) {
        Optional<Answer> answer = answerRepository.findById(answerDto.getId());
        if (!answer.isPresent() || answer.get().getQuestion() == null) {
            return createFailResult();
        }
        Optional<User> owner = userRepository.findById(answerDto.getUserDto().getId());
        if (answer.get().isVerified()) {
            owner.get().setQuestionStatus(owner.get().getQuestionStatus() - 1);
        }
        answerRepository.delete(answer.get());
        logger.info("Cevap silindi.id:{}", answer);
        return new JsonResponse<>(true);
    }

    @Override
    public JsonResponse<Boolean> likeAnswer(Long id) {
        JsonResponse<Boolean> response = new JsonResponse<>(Boolean.TRUE);
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
    public JsonResponse<Boolean> dislikeAnswer(Long id) {
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
