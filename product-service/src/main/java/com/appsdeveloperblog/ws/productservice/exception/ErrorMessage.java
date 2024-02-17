package com.appsdeveloperblog.ws.productservice.exception;

public class ErrorMessage {
    private long timestamp;
    private String message;
    private String details;

    public ErrorMessage() {
    }

    public ErrorMessage(long timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorMessage{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", message='").append(message).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
