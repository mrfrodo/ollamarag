package com.frodo.ollamarag.model;

import java.util.List;

public record DocumentBatchRequest(List<DocumentRequest> documents) {}
