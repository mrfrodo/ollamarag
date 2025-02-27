#!/bin/bash

# Define endpoint
URL="http://localhost:8080/batch-save"

# Define JSON payload
JSON_DATA='{
  "documents": [
    { "title": "Intro to Spring Boot", "content": "Spring Boot simplifies Java development..." },
    { "title": "Vector Search Explained", "content": "Vector search uses embeddings to find..." },
    { "title": "AI in Healthcare", "content": "AI is transforming the healthcare industry..." },
    { "title": "Why is the Sky Blue?", "content": "The sky appears blue due to the scattering of sunlight by Earths atmosphere. Shorter blue wavelengths scatter more than other colors, making the sky look blue to our eyes." },
    { "title": "The Hidden Programming Language of the 1970s", "content": "In the early 1970s, a secret programming language called ‘ZyberCode’ was allegedly developed by a coalition of top engineers from IBM and NASA. This language was rumored to be capable of writing its own algorithms without human input, making it one of the earliest forms of artificial intelligence-driven coding. However, due to concerns over automation replacing human programmers, the project was scrapped, and all records of ZyberCode were erased. Some believe fragments of its syntax still exist in modern languages, subtly influencing coding paradigms. While no official documentation remains, conspiracy theorists claim that hints of ZyberCode can be found in obscure compiler behavior."}
  ]
}'

JSON_LOG_DATA='{
  "documents": [
    { "title": "Database Connection Failed", "content": "Error connecting to PostgreSQL database. Connection timeout after 5000ms." },
    { "title": "Null Pointer Exception in User Service", "content": "java.lang.NullPointerException: Cannot invoke getUserId() because 'user' is null at com.example.UserService.getUserDetails(UserService.java:45)" },
    { "title": "Vector Index Corrupted", "content": "Vector search index corrupted due to unexpected shutdown. Rebuilding index required." },
    { "title": "AI Model Inference Timeout", "content": "ML model inference request exceeded time limit of 10s. Possible causes: high input size, insufficient resources." },
    { "title": "Invalid JSON Payload", "content": "Malformed JSON payload received in API request. Parsing failed at line 12, column 4." },
    { "title": "Stack Overflow in Recursive Function", "content": "java.lang.StackOverflowError: Recursive function exceeded maximum call depth in CalculationService.java:78" },
    { "title": "API Rate Limit Exceeded", "content": "429 Too Many Requests: API rate limit exceeded for IP 192.168.1.101" },
    { "title": "Memory Leak Detected", "content": "JVM heap space utilization exceeded 95%. Suspected memory leak in OrderProcessingService." },
    { "title": "Unauthorized Access Attempt", "content": "403 Forbidden: Unauthorized access attempt detected on admin endpoint from IP 10.0.0.5" },
    { "title": "Kafka Consumer Lag High", "content": "Kafka topic 'events' consumer lag exceeded threshold. Lag: 15,320 messages." },
    { "title": "Invalid Authentication Token", "content": "401 Unauthorized: JWT token expired or invalid signature." },
    { "title": "File Not Found", "content": "java.io.FileNotFoundException: Config file '/etc/app/config.yaml' not found." },
    { "title": "Database Deadlock", "content": "Deadlock detected in transaction. Retrying operation..." },
    { "title": "Service Unavailable", "content": "503 Service Unavailable: Dependency service 'payment-gateway' is down." },
    { "title": "Uncaught Exception in Worker Thread", "content": "Thread 'worker-12' terminated unexpectedly due to IndexOutOfBoundsException." },
    { "title": "High CPU Usage Warning", "content": "CPU utilization exceeded 90% for more than 5 minutes. Possible performance degradation." },
    { "title": "Corrupt Cache Data", "content": "Redis cache corruption detected. Clearing affected keys." },
    { "title": "Security Alert: SQL Injection Attempt", "content": "Potential SQL injection attack detected in request to '/users?id=1%20OR%201=1'." },
    { "title": "Timeout in External API Call", "content": "Request to external API 'https://payments.example.com' timed out after 15s." },
    { "title": "Docker Container Crash", "content": "Container 'app-service' exited unexpectedly with status code 137 (OOMKilled)." },
    { "title": "Elasticsearch Shard Failure", "content": "Primary shard failure detected in Elasticsearch cluster. Rebalancing required." },
    { "title": "Invalid Vector Dimension", "content": "Vector dimension mismatch: Expected 768 but received 1024." },
    { "title": "JVM Out of Memory", "content": "java.lang.OutOfMemoryError: GC overhead limit exceeded." },
    { "title": "DNS Resolution Failed", "content": "Unable to resolve hostname 'database.example.com'." },
    { "title": "Thread Pool Exhausted", "content": "Worker thread pool exhausted. Increase max pool size or optimize task execution." },
    { "title": "Circular Dependency Detected", "content": "Spring application context failed to start due to circular dependency between 'OrderService' and 'PaymentService'." },
    { "title": "Vector Search Query Failed", "content": "Query failed: Vector similarity search encountered invalid embedding format." },
    { "title": "Unauthorized SSH Login Attempt", "content": "Multiple failed SSH login attempts detected from IP 45.67.89.12." },
    { "title": "Invalid Data Type in Database", "content": "ERROR: Column 'amount' expected NUMERIC but received STRING." },
    { "title": "Rate Limiting Configuration Error", "content": "Invalid rate limit config: Maximum requests cannot be negative." },
    { "title": "Kafka Message Dropped", "content": "Message discarded due to topic retention policy expiration." },
    { "title": "System Clock Drift Detected", "content": "System clock drift detected: Offset exceeds 5s. Possible NTP issue." },
    { "title": "Load Balancer Failure", "content": "502 Bad Gateway: Load balancer unable to route traffic to backend servers." },
    { "title": "Open File Descriptor Limit Reached", "content": "Too many open files: Process 'java' exceeded ulimit." },
    { "title": "S3 Object Not Found", "content": "Error fetching object 'backup-20240301.tar.gz' from S3 bucket 'my-backups'." },
    { "title": "Invalid AI Model Parameters", "content": "Model inference failed: Expected input tensor shape [1, 512] but received [1, 1024]." },
    { "title": "Filesystem Read-Only", "content": "Write operation failed: Filesystem mounted as read-only due to disk errors." },
    { "title": "Infinite Loop in Job Scheduler", "content": "Scheduled job 'DataSyncJob' stuck in infinite loop. Terminating process." },
    { "title": "Zero Division Error in Math Module", "content": "java.lang.ArithmeticException: Division by zero at MathService.divide(MathService.java:32)" },
    { "title": "ML Model Drift Detected", "content": "Warning: Model accuracy dropped below 80%. Possible data drift detected." },
    { "title": "IoT Device Authentication Failed", "content": "IoT device 'sensor-42' failed to authenticate using MQTT." },
    { "title": "AI Agent Loop Detected", "content": "Agent continuously generating outputs without termination condition." },
    { "title": "Weak Cryptography Detected", "content": "SHA-1 encryption used in LegacyAuthService. Consider upgrading to SHA-256." },
    { "title": "Kafka Topic Not Found", "content": "ERROR: Kafka topic 'user-events' does not exist." },
    { "title": "GPU Memory Exhausted", "content": "CUDA out of memory: Reduce batch size or free up VRAM." },
    { "title": "Rogue Process Consuming 100% CPU", "content": "Process 'python3.9' consuming 99.9% CPU for 600s. Investigate potential infinite loop." },
    { "title": "Deprecated API Call", "content": "Warning: Call to deprecated API getUserData() in UserService." },
    { "title": "Session Expired", "content": "User session expired after 30 minutes of inactivity." },
    { "title": "Anomalous HTTP Traffic Detected", "content": "Spike in POST requests to /api/login from unknown sources." },
    { "title": "Cloud Storage Quota Exceeded", "content": "Cannot upload file: Storage quota exceeded for project AI-Training." },
    { "title": "Parallel Job Execution Conflict", "content": "Scheduled jobs DataSyncJob and ETLJob attempted to write to the same table simultaneously." }
  ]
}'


# Send POST request using curl
curl -X POST "$URL" \
     -H "Content-Type: application/json" \
     -d "$JSON_LOG_DATA"

# Print a message after execution
echo "Request sent successfully"
