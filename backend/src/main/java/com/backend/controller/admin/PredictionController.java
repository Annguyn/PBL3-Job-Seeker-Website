package com.backend.controller.admin;

import com.backend.entity.PredictionForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PredictionController {

    @PostMapping("/predict")
    public ResponseEntity<Double> predictSalary(@RequestBody PredictionForm predictionForm) {
        // Process the user's input data
        Map<String, Object> data = new HashMap<>();
        data.put("experience", predictionForm.getExperience());
        data.put("level_number", predictionForm.getLevelNumber());

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // Format for debugging
            String jsonData = mapper.writeValueAsString(data);
//            jsonData = jsonData.replaceAll("\\u00A0", " ");
            System.out.println("JSON Data: " + jsonData);

            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\nguye\\OneDrive - The University of Technology\\GITHUB CLONE REPO\\PBL3-Applying-AI-to-analyze-recruitment-information-on-Facebook\\DataAnalysis\\pred.py", jsonData);
            Process p = pb.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String s;
            while ((s = stdInput.readLine()) != null) {
                output.append(s);
            }

            StringBuilder error = new StringBuilder();
            while ((s = stdError.readLine()) != null) {
                error.append(s);
            }

            if (error.length() > 0) {
                System.err.println("Error in Python script: " + error.toString());
                return ResponseEntity.status(500).body(null);
            }

            if (output.length() > 0) {
                String outputStr = output.toString();
                Double predictedSalary = null;
                try {
                    predictedSalary = Double.parseDouble(outputStr);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format: " + outputStr);
                    return ResponseEntity.status(500).body(null);
                }
                return ResponseEntity.ok(predictedSalary);
            } else {
                System.err.println("Python script didn't return any output");
                return ResponseEntity.status(500).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
