package exercise.controller;

import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.model.Weather;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private final CityRepository cityRepository;

    @Autowired
    private final WeatherService weatherService;

    @GetMapping("/search")
    List<Weather> show(@RequestParam(required = false) String name) {
        if(name!=null)
        return weatherService.getWeatherData(name);
        else return weatherService.getWeatherData(null);
    }

    @GetMapping("/cities/{id}")
    Weather showById(@PathVariable Long id) {

        return weatherService.getWeatherData(cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found")).getName())
                .stream().findFirst().orElseThrow(()->new CityNotFoundException("City not found"));
    }


}

