package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.dto.DashboardDto;
import com.anercan.sorucevap.resource.CategoryResource;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.resource.DashboardResource;
import com.anercan.sorucevap.service.impl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dashboard")
@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    public DashboardService getService() {
        return dashboardService;
    }

    @PostMapping("/get-question-count")
    JsonResponse<DashboardResource> getQuestionCount(@RequestBody DashboardDto dashboardDto) {
        return getService().getQuestionCount(dashboardDto);
    }

    @PostMapping("/get-answer-count")
    JsonResponse<DashboardResource> getAnswerCount(@RequestBody DashboardDto dashboardDto) {
        return getService().getAnswerCount(dashboardDto);
    }

    @GetMapping("/get-categories")
    JsonResponse<List<CategoryResource>> getCategories(){
        return getService().getCategories(3);
    }
}
