package org.example.sink;

import org.example.LogLevel;
import org.example.LogMessage;

import java.util.Set;

public class ConsoleSink implements Sink {
    private final Set<LogLevel> supportedLevels;
    private final String timestampFormat;

    public ConsoleSink(Set<LogLevel> levels, String timestampFormat) {
        this.supportedLevels = levels;
        this.timestampFormat = timestampFormat;
    }

    @Override
    public void publish(LogMessage message) {
        System.out.println(message.format());
    }

    @Override
    public boolean supports(LogLevel level) {
        return supportedLevels.contains(level);
    }

    @Override
    public String getTimestampFormat() {
        return timestampFormat;
    }
}