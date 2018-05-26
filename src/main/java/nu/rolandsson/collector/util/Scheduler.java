package nu.rolandsson.collector.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
  
  @Scheduled(cron = "0 * * * *")
  public void buyHighPercentageReturnNotes(){
    System.out.println("Scheduler is running");
  }
}
