package org.example;

import org.example.handler.*;
public class Logger {
    private final LoggerConfig config;
    private final LogHandler handlerChain;

    public Logger(LoggerConfig config) {
        this.config = config;
        this.handlerChain = setupChain();
    }

    private LogHandler setupChain() {
        LogHandler debug = new DebugHandler();
        LogHandler info = new InfoHandler();
        LogHandler warn = new WarnHandler();
        LogHandler error = new ErrorHandler();
        LogHandler fatal = new FatalHandler();

        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);
        error.setNext(fatal);
        return debug;
    }

    public void log(LogLevel level, String namespace, String message) {
        handlerChain.handle(level, namespace, message, config);
    }
}