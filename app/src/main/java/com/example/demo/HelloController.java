package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, Object> getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("app", "DevOps Project 18 - GitOps Demo");
        response.put("version", "v1.0.0");
        response.put("message", "Deployed successfully via ArgoCD & Helm on Kubernetes!");
        return response;
    }
}
