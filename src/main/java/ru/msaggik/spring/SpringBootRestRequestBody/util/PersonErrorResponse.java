package ru.msaggik.spring.SpringBootRestRequestBody.util;

public class PersonErrorResponse {
    private String message; // сообщение ошибки
    private long timestamp; // отметка времени возникновения ошибки

    public PersonErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
