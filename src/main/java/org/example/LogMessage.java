package org.example;

public class LogMessage {
    private final String namespace;
    private final String content;
    private final LogLevel level;
    private final String timestamp;

    public LogMessage(String namespace, String content, LogLevel level, String timestamp) {
        this.namespace = namespace;
        this.content = content;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String format() {
        return String.format("%s [%s] %s", level.name(), timestamp, content);
    }

    public LogLevel getLevel() { return level; }
    public String getNamespace() { return namespace; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
}