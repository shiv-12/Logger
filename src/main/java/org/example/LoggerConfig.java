package org.example;

import org.example.sink.Sink;

import java.util.List;

public class LoggerConfig {
    private final List<Sink> sinks;

    public LoggerConfig(List<Sink> sinks) {
        this.sinks = sinks;
    }

    public List<Sink> getSinks() { return sinks; }
}
