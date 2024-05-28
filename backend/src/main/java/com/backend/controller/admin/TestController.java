package com.backend.controller.admin;

import com.backend.entity.Post;
import com.backend.entity.PredictionForm;
import com.backend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final PredictionController predictionController;
    private final PostService postService;

    public TestController(PredictionController predictionController, PostService postService) {
        this.predictionController = predictionController;
        this.postService = postService;
    }

    @GetMapping("/testPrediction")
    public ResponseEntity<String> testPrediction() {
        try {
            Post post = postService.getPostById(134);
            if (post == null) {
                return ResponseEntity.status(404).body("Post not found");
            }
            PredictionForm predictionForm = new PredictionForm(post);
            ResponseEntity<Double> response = predictionController.predictSalary(predictionForm);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok("The predicted salary is: " + response.getBody());
            } else {
                return ResponseEntity.status(500).body("Error predicting salary");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}