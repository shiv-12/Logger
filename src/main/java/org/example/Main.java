package org.example;

import org.example.sink.ConsoleSink;
import org.example.sink.FileSink;
import org.example.sink.Sink;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<LogLevel> fileLevels = EnumSet.of(LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR);
        Sink fileSink = new FileSink(fileLevels, "logs/app.log", "dd-MM-yyyy HH:mm:ss");

        Set<LogLevel> consoleLevels = EnumSet.of(LogLevel.INFO, LogLevel.ERROR);
        Sink consoleSink = new ConsoleSink(consoleLevels, "dd:MM:yyyy HH:mm:ss");

        LoggerConfig config = new LoggerConfig(List.of(fileSink, consoleSink));
        Logger logger = new Logger(config);

        for (int i = 0; i < 50; i++) {
            logger.log(LogLevel.INFO, "OrderService", "Order created successfully" + i);
            logger.log(LogLevel.WARN, "PaymentService", "Payment failed" + i);
        }

    }
}