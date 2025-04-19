
import org.example.LogLevel;
import org.example.LogMessage;
import org.example.sink.AsyncSink;
import org.example.sink.Sink;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class AsyncSinkTest {

    @Test
    void testAsyncPublish() {
        Sink consoleSink = mock(Sink.class); // Mock the actual sink (ConsoleSink)
        AsyncSink asyncSink = new AsyncSink(consoleSink); // Wrap it in AsyncSink
        LogMessage message = new LogMessage("PaymentService", "Payment failed", LogLevel.WARN, "2022-06-27 11:14:44,942");

        asyncSink.publish(message);
        verify(consoleSink, timeout(5000).times(1)).publish(message); // Verify that the message was passed to the console sink
    }
}