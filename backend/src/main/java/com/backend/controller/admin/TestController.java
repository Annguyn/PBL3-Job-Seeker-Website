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
        Post post = postService.getPostById(134);
        PredictionForm predictionForm = new PredictionForm(post);
        Double predictedSalary = predictionController.predictSalary(predictionForm);
        return ResponseEntity.ok("The predicted salary is: " + predictedSalary);
    }
}