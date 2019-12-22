package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.query.QuestionQuery;
import com.anercan.sorucevap.dto.DashboardDto;
import com.anercan.sorucevap.dto.QuestionFilterDto;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.enums.FilterStatus;
import com.anercan.sorucevap.resource.DashboardResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class DashboardService extends BaseService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionQuery questionQuery;

    public DashboardResource getQuestionCount(DashboardDto dto) {
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
            return null;
        }

        List<Question> questionListCurrent = questionRepository.findAll(questionQuery.filter(questionFilterDtoCurrent));
        List<Question> questionListPrevious = questionRepository.findAll(questionQuery.filter(questionFilterDtoPrevious));

        resource.setCurrentData(questionListCurrent.size() + "");
        resource.setPreviousData(questionListPrevious.size() + "");

        return resource;
    }


    public DashboardResource getAnswerCount(DashboardDto dto) {


        return null;
    }
}
