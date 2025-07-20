package service;

import java.util.Map;
import java.util.concurrent.*;

public class NotificationScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<Long, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public void schedule(Long userId, Runnable task, long initialDelay, long period, TimeUnit unit) {
        cancel(userId);
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, initialDelay, period, unit);
        tasks.put(userId, future);
    }

    public void cancel(Long userId) {
        ScheduledFuture<?> future = tasks.remove(userId);
        if (future != null) {
            future.cancel(false);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}