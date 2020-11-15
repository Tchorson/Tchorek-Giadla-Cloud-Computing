package com.example.cloudcomputinggiadlatchorek.service;

import com.example.cloudcomputinggiadlatchorek.config.TornadoStateConfig;
import com.example.cloudcomputinggiadlatchorek.logic.FuzzyLogic;
import com.example.cloudcomputinggiadlatchorek.model.TornadoState;
import com.example.cloudcomputinggiadlatchorek.repositories.TornadoStateRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
@EnableScheduling
public class TornadoStateService {

    TornadoStateRepository tornadoStateRepository;
    TornadoStateConfig tornadoStateConfig;
    FuzzyLogic fuzzyLogic;

    List<TornadoState> cache;

    @Autowired
    public TornadoStateService(TornadoStateRepository tornadoStateRepository, TornadoStateConfig tornadoStateConfig, FuzzyLogic fuzzyLogic) {
        this.tornadoStateRepository = tornadoStateRepository;
        this.tornadoStateConfig = tornadoStateConfig;
        this.fuzzyLogic = fuzzyLogic;
        this.cache = new LinkedList<>();
    }

    public Iterable<TornadoState> getAllRecords(){
        return tornadoStateRepository.findAll();
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void getWeatherApi(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(tornadoStateConfig.getApiURL(), String.class);
        System.out.println(response.getBody());
        JsonObject jobj = new Gson().fromJson(response.getBody(), JsonObject.class);
        double tempMin = jobj.getAsJsonObject("main").get("temp_min").getAsDouble();
        double tempMax = jobj.getAsJsonObject("main").get("temp_max").getAsDouble();
        double lat = jobj.getAsJsonObject("coord").get("lat").getAsDouble();
        double lng = jobj.getAsJsonObject("coord").get("lon").getAsDouble();
        double wind = jobj.getAsJsonObject("wind").get("speed").getAsDouble();
        double humidity = jobj.getAsJsonObject("main").get("humidity").getAsDouble();
        String location = jobj.getAsJsonPrimitive("name").getAsString();
        TornadoState t =new TornadoState(location, lat, lng, tempMax - tempMin, wind, humidity);
        cache.add(t);
        System.out.println(this.fuzzyLogic.inference(humidity, tempMax - tempMin, wind).toString());
        //System.out.println(humidity);
    }

    public List<TornadoState>getCache(){
        return this.cache;
    }
}
