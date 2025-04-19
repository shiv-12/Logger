package org.example;

import org.example.sink.Sink;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class LogHandler {
    protected LogHandler next;

    public void setNext(LogHandler next) {
        this.next = next;
    }

    public void handle(LogLevel level, String namespace, String content, LoggerConfig config) {
        if (canHandle(level)) {
            publishToSinks(namespace, content, level, config);
        } else if (next != null) {
            next.handle(level, namespace, content, config);
        }
    }

    protected abstract boolean canHandle(LogLevel level);

    private void publishToSinks(String namespace, String content, LogLevel level, LoggerConfig config) {
        for (Sink sink : config.getSinks()) {
            if (sink.supports(level)) {
                String timestamp = new SimpleDateFormat(sink.getTimestampFormat()).format(new Date());
                LogMessage message = new LogMessage(namespace, content, level, timestamp);
                sink.publish(message);
            }
        }
    }
}