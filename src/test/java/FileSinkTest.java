
import org.example.LogLevel;
import org.example.LogMessage;
import org.example.sink.FileSink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FileSinkTest {

    private FileSink fileSink;
    private LogMessage message;

    @BeforeEach
    void setUp() {
        fileSink = mock(FileSink.class);  // Mock the FileSink
        Set<LogLevel> levels = EnumSet.of(LogLevel.INFO);
        fileSink = new FileSink(levels, "logs/app.log", "dd-MM-yyyy HH:mm:ss");
        message = new LogMessage("OrderService", "Order created successfully", LogLevel.INFO, "2022-06-27 11:14:44,942");
    }

    @Test
    void testPublishMessage() {
        fileSink.publish(message);
        verify(fileSink, times(1)).publish(message);  // Verify that publish() was called once
    }
}