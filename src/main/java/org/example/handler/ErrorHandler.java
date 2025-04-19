package org.example.handler;

import org.example.LogHandler;
import org.example.LogLevel;

public class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) { return level == LogLevel.ERROR; }
}
