package com.example.cloudcomputinggiadlatchorek.logic;

import com.fuzzylite.*;
import com.fuzzylite.imex.FllImporter;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FuzzyLogic {

    private Engine engine;

    private InputVariable humidity;
    private InputVariable temperature;
    private InputVariable windSpeed;
    private OutputVariable dangerlvl;

    /**
     * Initialization engine of fuzzy logic
     */
    public FuzzyLogic() throws IOException, RuntimeException {
        File file = new File("D:\\JavaProjects\\Tchorek-Giadla-Cloud-Computing\\backend\\src\\main\\resources\\configFiles\\fuzzylogic.fll");
        StringBuilder status = new StringBuilder();
        try {
            this.engine = new FllImporter().fromFile(file);
            engine.isReady(status);
        } catch (IOException | RuntimeException e1) {
            System.err.println("Engine can not start.");
        }

        //System.out.println(engine.getOutputVariables().toString());

        InputVariable humidity = engine.getInputVariable("humidity");
        InputVariable temperature = engine.getInputVariable("temperature");
        InputVariable windSpeed = engine.getInputVariable("windSpeed");

        OutputVariable dangerlvl = engine.getOutputVariable("dangerlvl");
    }


}
