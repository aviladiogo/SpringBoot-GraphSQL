package com.mines;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    
    @Scheduled(fixedDelay = 30000)
    public void execute() throws InterruptedException{
        System.out.println("Rodando: " + LocalTime.now());
    }
}
