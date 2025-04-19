package org.example.handler;

import org.example.LogHandler;
import org.example.LogLevel;

public class WarnHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) { return level == LogLevel.WARN; }
}
