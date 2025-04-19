import org.example.LogLevel;
import org.example.LogMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogMessageTest {

    @Test
    void testLogMessageFormatting() {
        LogMessage message = new LogMessage("OrderService", "Order created successfully", LogLevel.INFO, "2022-06-27 11:14:44,942");
        String formattedMessage = message.format();
        assertEquals("INFO [2022-06-27 11:14:44,942] Order created successfully", formattedMessage);
    }
}