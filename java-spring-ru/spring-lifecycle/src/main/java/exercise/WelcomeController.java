package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController{

    @Autowired
    Meal meal;

    @Autowired
    MyApplicationConfig dt;

    @GetMapping("/daytime")
    public String hello(){
        return String.format("It is %s now. Enjoy your %s", dt.dayTime().getName(), meal.getMealForDaytime(dt.dayTime().getName()) );

    }


}

// BEGIN

// END
