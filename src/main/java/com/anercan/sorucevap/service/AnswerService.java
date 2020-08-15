package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.AnswerRepository;
import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.client.dto.AnswerDto;
import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService extends AbstractEntityService<Answer> {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    BaseRepository<Answer, Long> getRepository() {
        return answerRepository;
    }

    public ServiceResult<List<Answer>> getAnswersByQuestionId(Long id) {
        return createServiceResult(questionRepository.findById(id).get().getAnswer()); //todo
    }

    public ServiceResult<Boolean> createAnswer(AnswerDto answerDto) {
        /*Optional<Question> question = questionRepository.findById(answerDto.getQuestionDto().getId());
        if(question.isPresent()){
            new JsonResponse<>().setCode(-1);
        }*/
        Answer answer = new Answer();
        User owner = userRepository.findById(answerDto.getUserDto().getId()).get(); //id yı tokendan al
        Question question = questionRepository.findById(answerDto.getQuestionDto().getId()).get();
        answer.setQuestion(question);
        answer.setUser(owner);
        answer.setContent(answerDto.getContent());
        log.info("Cevap oluşturuldu.Cevap:{}", answer.getId());
        super.save(answer);
        return createServiceResult(Boolean.TRUE);
    }


    public ServiceResult<Boolean> deleteAnswer(AnswerDto answerDto) {
        Optional<Answer> answer = answerRepository.findById(answerDto.getId());
        if (!answer.isPresent() || answer.get().getQuestion() == null) {
            return createFailResult();
        }
        Optional<User> owner = userRepository.findById(answerDto.getUserDto().getId());
        if (answer.get().isVerified()) {
            owner.get().setQuestionStatus(owner.get().getQuestionStatus() - 1);
        }
        answerRepository.delete(answer.get());
        log.info("Cevap silindi.id:{}", answer);
        return createServiceResult(Boolean.TRUE);
    }


    public ServiceResult<Boolean> likeAnswer(Long id) {
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
        log.info("Cevap likelandi.Cevap id:{}",answer.getId());
        response.setValue(answerRepository.save(answer));
        }
        else{
            response.setCode(-1);
            response.setMessage("fail");
        }*/
        return createServiceResult(true);
    }


    public ServiceResult<Boolean> dislikeAnswer(Long id) {
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
        log.info("Cevap dislikelandi.Cevap id:{}",answer.getId());*/
        return createServiceResult(answerRepository.save(answer));
    }


}
