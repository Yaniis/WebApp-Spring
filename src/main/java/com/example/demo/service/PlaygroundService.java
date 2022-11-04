package com.example.demo.service;

import com.example.demo.info.Timerinfo;
import com.example.demo.jobs.DeleteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaygroundService {

    private final SchedulerService schedulerService;

    @Autowired
    public PlaygroundService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }


    public void runDeleteJob(){
        final Timerinfo info = new Timerinfo();
        info.setTotalFireCount(1);
        info.setRepeatInterval(2000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("Delete data");

        schedulerService.schedule(DeleteUser.class, info);

    }
}
