package com.example.cloudcomputinggiadlatchorek.service;

import com.example.cloudcomputinggiadlatchorek.config.TornadoStateConfig;
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

    List<TornadoState> cache;

    @Autowired
    public TornadoStateService(TornadoStateRepository tornadoStateRepository, TornadoStateConfig tornadoStateConfig) {
        this.tornadoStateRepository = tornadoStateRepository;
        this.tornadoStateConfig = tornadoStateConfig;
        this.cache = new LinkedList<>();
    }

    public Iterable<TornadoState> getAllRecords(){
        return tornadoStateRepository.findAll();
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void getWeatherApi(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(tornadoStateConfig.getApiURL(), String.class);
        System.out.println(response.getBody());
        JsonObject jobj = new Gson().fromJson(response.getBody(), JsonObject.class);


        System.out.println(jobj.getAsJsonObject("main").get("temp_min"));
        System.out.println(jobj.getAsJsonObject("main").get("temp_max"));
        System.out.println(jobj.getAsJsonObject("coord").get("lon"));
        System.out.println(jobj.getAsJsonObject("coord").get("lat"));
    }
}
