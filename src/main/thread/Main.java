package main.thread;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("run/...");
        ScheduledExecutorService ex =Executors.newScheduledThreadPool(1);
        ex.schedule(new MyRunnable(1),2,TimeUnit.SECONDS);
        ex.shutdown();
    }

    public static void cachedThreadPool() {
        ExecutorService service = new ThreadPoolExecutor(10, 15, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        ExecutorService service1 = Executors.newCachedThreadPool();
    }

    public static void fixedThreadPool() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.submit(new MyRunnable(i));
        }
    }

    public static void singleThreadExecutor() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future f = service.submit(new MyRunnable(1));
        service.submit(new MyRunnable(2));
        service.submit(new MyRunnable(3));
        service.submit(new MyRunnable(4));
        service.submit(new MyRunnable(5));

    }
}
