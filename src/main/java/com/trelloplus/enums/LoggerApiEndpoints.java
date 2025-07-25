package com.trelloplus.enums;

public enum LoggerApiEndpoints {
    LOGGER_URI("http://localhost:8081/logs");

    private final String url;

    LoggerApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
