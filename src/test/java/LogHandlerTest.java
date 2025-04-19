
import org.example.LogHandler;
import org.example.LogLevel;
import org.example.LogMessage;
import org.example.handler.DebugHandler;
import org.example.handler.InfoHandler;
import org.example.handler.WarnHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogHandlerTest {

    @Test
    void testLogHandlerChains() {
        LogHandler debugHandler = new DebugHandler();
        LogHandler infoHandler = new InfoHandler();
        LogHandler warnHandler = new WarnHandler();

        debugHandler.setNext(infoHandler);
        infoHandler.setNext(warnHandler);

        LogMessage message = new LogMessage("OrderService", "Test debug message", LogLevel.DEBUG, "2022-06-27 11:14:44,942");
        debugHandler.handle(LogLevel.DEBUG, "OrderService", "Test debug message", null);
    }
}