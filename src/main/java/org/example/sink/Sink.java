package org.example.sink;

import org.example.LogLevel;
import org.example.LogMessage;

public interface Sink {
    void publish(LogMessage message);

    boolean supports(LogLevel level);

    String getTimestampFormat();
}
