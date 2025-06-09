#!/bin/bash

# Create logs directory if it doesn't exist
mkdir -p logs/nacos

# Set JVM parameters to disable Nacos default logging
JAVA_OPTS="-Dnacos.logging.default.config.enabled=false -Dnacos.logging.default.naming.enabled=false -Dnacos.logging.path=logs/nacos"

# Start the application with the JVM parameters
java $JAVA_OPTS -jar target/backend-0.0.1-SNAPSHOT.jar 