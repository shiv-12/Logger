package org.example.sink;

import org.example.LogLevel;
import org.example.LogMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class AsyncSink implements Sink {
    private final Sink delegate;
    private final BlockingQueue<LogMessage> queue = new LinkedBlockingQueue<>();
    private final Thread worker;

    public AsyncSink(Sink delegate) {
        this.delegate = delegate;
        this.worker = new Thread(() -> {
            while (true) {
                try {
                    LogMessage message = queue.take();
                    delegate.publish(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        this.worker.setDaemon(true);
        this.worker.start();
    }

    @Override
    public void publish(LogMessage message) {
        queue.offer(message);
    }

    @Override
    public boolean supports(LogLevel level) {
        return delegate.supports(level);
    }

    @Override
    public String getTimestampFormat() {
        return delegate.getTimestampFormat();
    }
}