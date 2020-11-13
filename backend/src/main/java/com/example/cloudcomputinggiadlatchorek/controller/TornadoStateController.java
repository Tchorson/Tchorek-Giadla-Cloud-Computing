package com.example.cloudcomputinggiadlatchorek.controller;

import com.example.cloudcomputinggiadlatchorek.service.TornadoStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TornadoStateController {

    @Autowired
    TornadoStateService tornadoStateService;

    @GetMapping(path = "/getAllRecords")
    public void getCollections(){
        tornadoStateService.getAllRecords();
    }


}
