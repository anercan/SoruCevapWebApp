package com.anercan.sorucevap.controller.dashboard;

import com.anercan.sorucevap.dto.DashboardDto;
import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.resource.DashboardResource;
import com.anercan.sorucevap.service.impl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dashboard")
@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    public DashboardService getService() {
        return dashboardService;
    }

    @PostMapping("/getQuestionCount")
    JsonResponse<DashboardResource> getQuestionCount(@RequestBody DashboardDto dashboardDto) {
        return new JsonResponse<>(getService().getQuestionCount(dashboardDto));
    }

    @PostMapping("/getAnswerCount")
    JsonResponse<DashboardResource> getAnswerCount(@RequestBody DashboardDto dashboardDto) {
        return new JsonResponse<>(getService().getAnswerCount(dashboardDto));
    }
}
