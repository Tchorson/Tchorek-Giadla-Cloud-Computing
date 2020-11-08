package com.example.cloudcomputinggiadlatchorek.controller;

import com.example.cloudcomputinggiadlatchorek.repositories.TornadoStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TornadoStateController {

    @Autowired
    TornadoStateRepository tornadoStateRepository;

    @GetMapping(path = "/test")
    public void getCollections(){
        tornadoStateRepository.findAll().forEach(tornadoState -> System.out.println(tornadoState.toString()));
    }
}
