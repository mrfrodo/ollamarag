package com.frodo.ollamarag.service;

import org.springframework.beans.factory.annotation.Value;

public class GeminiService {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model-name}")
    private String modelName;

    @Value("${gemini.location}")
    private String location;
}
