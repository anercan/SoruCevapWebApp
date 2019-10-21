package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.entity.dto.QuestionDto;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService {

    //todo baseserviceden al repoları buraya

    @Override
    public Optional<Question> getById(Long id) {
        if (!questionRepository.findById(id).isPresent()) {
            new JsonResponse<>().setCode(-1);
        }
        logger.info("Soru id ile getirildi.id:{}",id);
        return questionRepository.findById(id);
    }

    @Override
    public JsonResponse<Boolean> createQuestion(QuestionDto questionDto) {
        User user = userRepository.findById(questionDto.getUserDto().getId()).get();
        if(user!=null && user.getQuestionStatus()>0) {
            Question question = new Question();
            question.setUser(user);
            question.setTitle(questionDto.getTitle());
            question.setContent(questionDto.getContent());
            user.setQuestionStatus(user.getQuestionStatus() - 1); //Soru hakkı indirildi
            question.setDate(date);
            logger.info("Soru oluşturuldu.Soru:{}",question);
            questionRepository.save(question);
            return new JsonResponse<>(true,0);

        }
        else
            logger.info("Soru Hakkı ya da user yok.user:{}",user);
            return  new JsonResponse<>(false,-1);
    }

    @Override
    public JsonResponse<Boolean> deleteQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).get();
        if(question.getAnswer().size()>50){
            logger.info("Soru silinme talebi oluşturuldu.Soru:{}",question); //todo talep
            return new JsonResponse<>(false,-1);
        }
        logger.info("Soru silindi.Soru:{}",question);
        questionRepository.delete(question);
        question.getUser().setQuestionStatus(question.getUser().getQuestionStatus()-1);
        return new JsonResponse<>(true,0);
    }
}