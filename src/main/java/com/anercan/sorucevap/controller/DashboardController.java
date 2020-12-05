package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.client.dto.DashboardDto;
import com.anercan.sorucevap.client.resource.CategoryResource;
import com.anercan.sorucevap.client.resource.DashboardResource;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dashboard")
@RestController
public class DashboardController extends BaseController {

    @Autowired
    DashboardService dashboardService;

    public DashboardService getService() {
        return dashboardService;
    }

    @PostMapping("/get-question-count")
    JsonResponse<DashboardResource> getQuestionCount(@RequestBody DashboardDto dashboardDto) {
        return createJsonResponse(getService().getQuestionCount(dashboardDto));
    }

    @PostMapping("/get-answer-count")
    JsonResponse<DashboardResource> getAnswerCount(@RequestBody DashboardDto dashboardDto) {
        return createJsonResponse(getService().getAnswerCount(dashboardDto));
    }

    @GetMapping("/get-categories")
    JsonResponse<List<CategoryResource>> getCategories() {
        return createJsonResponse(getService().getCategories(3));
    }
}
