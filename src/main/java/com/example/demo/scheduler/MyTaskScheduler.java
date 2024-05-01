package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.demo.service.ProService;

@Component
public class MyTaskScheduler {
    private final ProService proService;

    @Autowired
    public MyTaskScheduler(ProService proService) {
        this.proService = proService;
    }

    @Scheduled(cron = "0 30 12 * * *")
    public void scheduleEmailNotifications() {
        proService.checkAndSendEmailNotifications();
    }
}
