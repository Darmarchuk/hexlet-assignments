package exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Weather {

          private String name;
          private Short temperature;
          private String cloudy;
          private Short wind;
          private Short  humidity;


   public Weather(String json) {
       ObjectMapper om=new ObjectMapper();
       try {
           Weather weather= om.readValue(json, Weather.class);
           this.name= weather.getName();
           this.temperature=weather.getTemperature();
           this.cloudy=weather.getCloudy();
           this.humidity=weather.getHumidity();
           this.wind=weather.getWind();
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }

   }
}
