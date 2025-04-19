
import org.example.LogLevel;
import org.example.LogMessage;
import org.example.Logger;
import org.example.LoggerConfig;
import org.example.sink.Sink;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    void testLoggerLogsToAppropriateSinks() {
        Sink fileSink = mock(Sink.class);
        Sink consoleSink = mock(Sink.class);
        Set<LogLevel> fileLevels = EnumSet.of(LogLevel.INFO);
        Set<LogLevel> consoleLevels = EnumSet.of(LogLevel.ERROR);
        LoggerConfig config = new LoggerConfig(List.of(fileSink, consoleSink));
        Logger logger = new Logger(config);

        logger.log(LogLevel.INFO, "OrderService", "Order created successfully");
        logger.log(LogLevel.ERROR, "PaymentService", "Payment failed");

        verify(fileSink, times(1)).publish(any(LogMessage.class));  // Ensure fileSink was called for INFO
        verify(consoleSink, times(1)).publish(any(LogMessage.class)); // Ensure consoleSink was called for ERROR
    }
}