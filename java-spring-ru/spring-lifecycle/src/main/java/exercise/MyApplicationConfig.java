package exercise;

import exercise.daytimes.Day;
import exercise.daytimes.Daytime;
import exercise.daytimes.Evening;
import exercise.daytimes.Morning;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;


@Configuration
public class MyApplicationConfig{

//    @RequestScope
    @Bean
    public Daytime  dayTime(){
        LocalDateTime dt=LocalDateTime.now();
        if(dt.getHour()>=6 && dt.getHour()<12 ) return  new Morning();
        if(dt.getHour()>=12 && dt.getHour()<18 ) return  new Day();
        if(dt.getHour()>=18 && dt.getHour()<23 ) return  new Evening();
        else return new Night();

    };




}
