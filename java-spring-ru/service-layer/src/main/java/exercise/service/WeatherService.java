package exercise.service;

import exercise.HttpClient;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Weather;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;


    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    public List<Weather> getWeatherData(String filter){
        List<City> cities;
        if(filter!=null)
        cities=cityRepository.findCitiesByNameStartingWithIgnoreCase(filter);
        else cities=cityRepository.findAllByOrderByName();

        return cities.stream().map(c->client.get("v2/cities/"+ c.getName()))
                .map(w->new Weather(w))
                .collect(Collectors.toList());

    }



}
