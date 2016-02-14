package jamp.main;

import jamp.task.ComplexTask;
import jamp.task.SimpleTask;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Second {

    public static final int SIMPLE_THREAD_COUNT = 6;

    private static Logger logger = Logger.getLogger(Second.class);

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Start.");

        ExecutorService executorService = Executors.newCachedThreadPool();

        Lock firstLock = new ReentrantLock();
        Lock secondLock = new ReentrantLock();

        executorService.submit(new ComplexTask(firstLock, secondLock));
        executorService.submit(new ComplexTask(secondLock, firstLock));

        for (int i = 1; i <= SIMPLE_THREAD_COUNT; ++i) {
            if (!(i > SIMPLE_THREAD_COUNT / 2)) {
                executorService.submit(new SimpleTask(firstLock));
            } else {
                executorService.submit(new SimpleTask(secondLock));
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Finish.");
    }
}
