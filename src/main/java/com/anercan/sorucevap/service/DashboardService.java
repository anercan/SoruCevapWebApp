package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.AnswerRepository;
import com.anercan.sorucevap.dao.CategoryRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.query.AnswerQuery;
import com.anercan.sorucevap.dao.query.QuestionQuery;
import com.anercan.sorucevap.client.dto.AnswerFilterDto;
import com.anercan.sorucevap.client.dto.DashboardDto;
import com.anercan.sorucevap.client.dto.QuestionFilterDto;
import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Category;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.enums.FilterStatus;
import com.anercan.sorucevap.client.resource.CategoryResource;
import com.anercan.sorucevap.client.resource.DashboardResource;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService extends BaseService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    QuestionQuery questionQuery;

    @Autowired
    AnswerQuery answerQuery;

    @Autowired
    AnswerRepository answerRepository;


    public ServiceResult<DashboardResource> getQuestionCount(DashboardDto dto) {
        QuestionFilterDto questionFilterDtoCurrent = new QuestionFilterDto();
        QuestionFilterDto questionFilterDtoPrevious = new QuestionFilterDto();
        DashboardResource resource = new DashboardResource();

        LocalDate localDate = LocalDate.now();

        if (dto.getFilterStatus().equals(FilterStatus.DAILY)) {
            questionFilterDtoCurrent.setStartDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            questionFilterDtoPrevious.setStartDate(Date.from(localDate.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            questionFilterDtoPrevious.setEndDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        } else if (dto.getFilterStatus().equals(FilterStatus.MONTHLY)) {
            questionFilterDtoCurrent.setStartDate(Date.from(localDate.minusMonths(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            questionFilterDtoPrevious.setStartDate(Date.from(localDate.minusMonths(2).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            questionFilterDtoPrevious.setEndDate(Date.from(localDate.minusMonths(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            return createFailResult();
        }

        List<Question> questionListCurrent = questionRepository.findAll(questionQuery.filter(questionFilterDtoCurrent));
        List<Question> questionListPrevious = questionRepository.findAll(questionQuery.filter(questionFilterDtoPrevious));

        resource.setCurrentData(questionListCurrent.size() + "");
        resource.setPreviousData(questionListPrevious.size() + "");

        return createServiceResult(resource);
    }


    public ServiceResult<DashboardResource> getAnswerCount(DashboardDto dto) {

        AnswerFilterDto answerFilterDtoCurrent = new AnswerFilterDto();
        AnswerFilterDto answerFilterDtoPrevious = new AnswerFilterDto();
        DashboardResource resource = new DashboardResource();

        LocalDate localDate = LocalDate.now();

        if (dto.getFilterStatus().equals(FilterStatus.DAILY)) {
            answerFilterDtoCurrent.setStartDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            answerFilterDtoPrevious.setStartDate(Date.from(localDate.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            answerFilterDtoPrevious.setEndDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        } else if (dto.getFilterStatus().equals(FilterStatus.MONTHLY)) {
            answerFilterDtoCurrent.setStartDate(Date.from(localDate.minusMonths(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            answerFilterDtoPrevious.setStartDate(Date.from(localDate.minusMonths(2).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            answerFilterDtoPrevious.setEndDate(Date.from(localDate.minusMonths(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            return createFailResult();
        }

        List<Answer> answerListCurrent = answerRepository.findAll(answerQuery.filter(answerFilterDtoCurrent));
        List<Answer> answerListPrevious = answerRepository.findAll(answerQuery.filter(answerFilterDtoPrevious));

        resource.setCurrentData(answerListCurrent.size() + "");
        resource.setPreviousData(answerListPrevious.size() + "");

        return createServiceResult(resource);
    }

    public ServiceResult<List<CategoryResource>> getCategories(int numberOfCategory) {
        HashMap<Long, Integer> categoryMap = new HashMap<>();
        List<CategoryResource> resources = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll();

        categories.forEach(category -> categoryMap.put(category.getId(), questionRepository.getNumberByCategory(category.getId())));
        HashMap<Long, Integer> sortedCategoryMap = categoryMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, HashMap::new));

        if (categoryMap.isEmpty()) {
            return createFailResult();
        }

        for (int i = 0; i < numberOfCategory; i++) {
            String categoryId = sortedCategoryMap.keySet().toArray()[i].toString();
            resources.add(
                    new CategoryResource(categories.stream().filter(
                            category -> category.getId().toString().equals(categoryId)).findAny().get().getName()
                            , sortedCategoryMap.get(Long.parseLong(categoryId)))
            );

        }
        Collections.shuffle(resources);
        return createServiceResult(resources);
    }
}
