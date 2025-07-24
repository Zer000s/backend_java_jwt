package com.java.java_test_jwt.utils;

public class Utils {
    private static final String UPLOADED_FOLDER = "src/main/resources/static/files/";
    private static final String RABBITMQ_PATH = "localhost";

    public String returnUploadFolder() {
        return UPLOADED_FOLDER;
    }

    public String returnRabbitMQPath() {
        return RABBITMQ_PATH;
    }
}